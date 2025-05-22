package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import tool.Action;

public class LogoutAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
    	User user = new User();
        user.setAuthenticated(false);
        session.setAttribute("user", user);

        if (user == null || !user.isAuthenticated()) {
            session.invalidate();
            return "scoremanager/logout.jsp";
        }

        return "scoremanager.Manu.action";
    }
}
