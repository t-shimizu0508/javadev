package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.User;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();

		String id=request.getParameter("loginId");
		String password=request.getParameter("password");

		TeacherDao dao=new TeacherDao();
		User user = new User();
		Teacher teacher=dao.login(id, password);

		if (teacher!=null) {
			session.setAttribute("teacher", teacher);
			session.setAttribute("teacherName", teacher.getName());
			user.setAuthenticated(true);
			session.setAttribute("user", user);
			return "scoremanager.Manu.action";
		}

		request.setAttribute("error", "ログインに失敗しました。IDまたはパスワードが間違っています。");
		return "login.jsp";
	}
}

