package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.h2.tools.Server;

@WebListener
public class H2ServerInitializer implements ServletContextListener, LifecycleListener {
    private static Server server; // H2サーバーインスタンス

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        synchronized (H2ServerInitializer.class) {
            if (isH2RunningGlobally() || isH2Running()) {
                System.out.println("H2 データベースは既に起動しています。新たに起動しません。");
                return;
            }

            try {
                System.out.println("H2 データベースを起動します...");
                server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
                System.setProperty("H2_STARTED", "true");
                System.out.println("H2 データベースが起動しました！ ポート: 9092");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        stopH2Server();
    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        if ("before_stop".equals(event.getType())) { // Tomcatが停止するときに実行
            System.out.println("Tomcatのシャットダウンを検知。H2を停止します...");
            stopH2Server();
        }
    }

    private void stopH2Server() {
        synchronized (H2ServerInitializer.class) {
            if (server != null) {
                System.out.println("H2 データベースを停止します...");
                server.stop();
                System.clearProperty("H2_STARTED");
                System.out.println("H2 データベースが停止しました。");
                server = null;
            }
        }
    }

    private boolean isH2RunningGlobally() {
        return "true".equals(System.getProperty("H2_STARTED"));
    }

    private boolean isH2Running() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/javadev", "sa", "")) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}