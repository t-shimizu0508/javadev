package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");
        if (school == null) {
            request.setAttribute("error", "学校情報がありません。");
            return "login.jsp";
        }

        StudentDao studentdao = new StudentDao();

        // パラメータ取得
        String entYearParam = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String isAttendParam = request.getParameter("isAttend");

        List<Student> studentList;

        boolean hasEntYear = entYearParam != null && !entYearParam.isEmpty();
        boolean hasClassNum = classNum != null && !classNum.isEmpty();
        boolean hasIsAttend = isAttendParam != null && isAttendParam.equals("true");

        if (hasEntYear && hasClassNum && hasIsAttend) {
            int entYear = Integer.parseInt(entYearParam);
            studentList = studentdao.filter(school, entYear, classNum, hasIsAttend);
        } else if (hasEntYear && hasClassNum) {
            int entYear = Integer.parseInt(entYearParam);
            studentList = studentdao.filter(school, entYear, classNum);
        } else if (hasEntYear && hasIsAttend) {
            int entYear = Integer.parseInt(entYearParam);
            studentList = studentdao.filter(school, entYear,hasIsAttend);
        } else if (hasEntYear) {
            int entYear = Integer.parseInt(entYearParam);
            studentList = studentdao.filter(school, entYear);
        } else if (hasClassNum) {
        	request.setAttribute("error", "クラスを指定する場合は学年も指定してください。");
        	 studentList = new ArrayList<>();
        } else if (hasIsAttend) {
            studentList = studentdao.filter(school, hasIsAttend);
        } else {
            // 何も指定がない場合は学校だけで全件取得
            studentList = studentdao.filter(school);
        }

        request.setAttribute("studentList", studentList);

        // 明示的にJSPにフォワード
        request.getRequestDispatcher("/scoremanager/student_list.jsp").forward(request, response);

        return null; // フォワード済みなのでnull返し
    }
}
