package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import tool.Action;

public class ManuAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user"); // セッションからUserを取得

        if (user != null) {
            // 認証されていたらメニューページへ
            return "scoremanager/menu.jsp";
        } else {
            // 未認証ならログインページへ
            return "login.jsp";
        }

	}
}