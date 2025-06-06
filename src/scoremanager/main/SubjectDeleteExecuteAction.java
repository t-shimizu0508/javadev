package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }

	        String cd = request.getParameter("subject_cd");
	        String name = request.getParameter("subject_name");

	        Subject subject = new Subject();
	        subject.setName(name);
	        subject.setCd(cd);
	        subject.setSchool(school);

	        SubjectDao dao = new SubjectDao();
	        dao.delete(subject);

	     // 明示的にJSPにフォワード
	        request.getRequestDispatcher("/scoremanager/subject_delete_done.jsp").forward(request, response);

	        return null;
	 }

}
