package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		if (school == null) {
			return "login.jsp";
		}
		String no = request.getParameter("no");
		int entYear = Integer.parseInt(request.getParameter("ent_year"));
		String name = request.getParameter("name"); // 氏名（入力）
		String classNum = request.getParameter("class_num"); // クラス（select）
		boolean isAttend = "true".equals(request.getParameter("is_attend"));// 在学中（checkbox）

		StudentDao studentdao = new StudentDao();
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setClassNum(classNum);
		student.setEntYear(entYear);
		student.setAttend(isAttend);
		student.setSchool(school);
		;


		studentdao.save(student);
		return "scoremanager/student_update_done.jsp";
	}
}
