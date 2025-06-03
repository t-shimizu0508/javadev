package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		if (school == null) {
			return "login.jsp";
		}
		String no = request.getParameter("no");
		StudentDao studentdao = new StudentDao();
        Student student = studentdao.get(no);
        request.setAttribute("student", student);

		return "scoremanager/student_update.jsp";
	}
}
