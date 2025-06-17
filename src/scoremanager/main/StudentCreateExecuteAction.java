package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");
        if (school == null) {
            request.setAttribute("error", "学校情報がありません。");
            return "login.jsp";
        }

        String entYear = request.getParameter("entYear");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        System.out.println(entYear);
        System.out.println(no);
        System.out.println(name);
        System.out.println(classNum);

        if (entYear == null || entYear.isEmpty()) {
                request.setAttribute("error", "入学年度を選択してください。");
                return "scoremanager/student_create.jsp";
            }

		return "scoremanager/student_create_done.jsp";
	}

}
