package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }
	        String cd = request.getParameter("cd");
	        SubjectDao dao = new SubjectDao();
	        Subject subject = dao.get(cd, school);

	        request.setAttribute("subject", subject);

	        // 更新フォーム画面へフォワード
	        return "scoremanager/subject_update.jsp";
	 }
}
