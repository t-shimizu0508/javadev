package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }
	        SubjectDao dao = new SubjectDao();
	        List<Subject> subjectList = dao.filter(school);
	        request.setAttribute("subjectList", subjectList);

	        request.getRequestDispatcher("/scoremanager/subject_list.jsp").forward(request, response);
	        return null;
	 }
}
