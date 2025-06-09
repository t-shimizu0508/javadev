package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;


public class SubjectCreateExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		 HttpSession session = request.getSession();
	     School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }


		 String cd = request.getParameter("cd");
	     String name = request.getParameter("name");

	  // 全角が含まれているかどうかのチェック
	     if (!cd.matches("^[a-zA-Z0-9]{1,3}$")) {
	         if (cd.length() > 3) {
	             request.setAttribute("error", "科目コードは3文字以内で入力してください。");
	         } else {
	             request.setAttribute("error", "科目コードに全角文字は使用できません。");
	         }
	         return "scoremanager/subject_create.jsp";
	     }


	     SubjectDao dao = new SubjectDao();

	        Subject existing = dao.get(cd, school);
	        if (existing != null) {
	            request.setAttribute("cd", cd);
	            request.setAttribute("error", "科目コードが重複しています。");
	            return "scoremanager/subject_create.jsp";
	        }

	        Subject subject = new Subject();
	        subject.setCd(cd);
	        subject.setName(name);
	        subject.setSchool(school);

	        dao.insert(subject);

		return "scoremanager/subject_create_done.jsp";
	}

}
