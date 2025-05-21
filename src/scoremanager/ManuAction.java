package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class ManuAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

	    HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("isAuthenticated");

        if (teacher.isAuthenticated()==true) {
            // 認証済みならメニュー画面へ
            return "scoremanager/menu.jsp";
        } else {
            // 未認証ならログインページに戻すなど
            return "scoremanager/login.jsp";
        }

	}
}