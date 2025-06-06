package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction  extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }

	        String cd = request.getParameter("cd");
	        String name = request.getParameter("name");

	        Subject subject = new Subject();
	        subject.setName(name);
	        subject.setCd(cd);
	        subject.setSchool(school);

	        SubjectDao dao = new SubjectDao();
	        dao.save(subject);

	        return "scoremanager/subject_update_done.jsp";
	 }
}
