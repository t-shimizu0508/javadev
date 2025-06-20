package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import dao.StudentDao;
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

        String entYearStr = request.getParameter("entYear");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");

        if (entYearStr == null || entYearStr.isEmpty()) {
                request.setAttribute("error", "入学年度を選択してください。");
                return "scoremanager.main.StudentCreate.action";
            }

        if (no != null && !no.equals("") && no.matches(".*[０-９].*")) {
            request.setAttribute("error", "学生番号は半角数字で入力してください。");
            return "scoremanager.main.StudentCreate.action";
        }

        try {
            Integer.parseInt(no); // 数字かどうかをチェック（整数以外は例外）
        } catch (NumberFormatException e) {
            request.setAttribute("error", "学生番号は数字で入力してください。");
            return "scoremanager.main.StudentCreate.action";
        }

        StudentDao dao = new StudentDao();
        if (dao.exists(no)) {
            request.setAttribute("error", "その学生番号はすでに存在しています。");
            return "scoremanager.main.StudentCreate.action";
        }

        int entYear = Integer.parseInt(entYearStr);
        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setClassNum(classNum);
        s.setEntYear(entYear);
        s.setAttend(false);
        s.setSchool(school);
        dao.insert(s);

		return "scoremanager/student_create_done.jsp";
	}

}
