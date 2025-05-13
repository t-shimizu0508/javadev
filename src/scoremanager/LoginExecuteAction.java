package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		HttpSession session=request.getSession();

		String id=request.getParameter("ID");
		String password=request.getParameter("password");

		TeacherDao dao=new TeacherDao();
		Teacher teacher=dao.search(id, password);

		if (teacher!=null) {
			session.setAttribute("teacher", teacher);
			return "scoremanager/index.jsp";
		}

		return "error.jsp";
	}
}

