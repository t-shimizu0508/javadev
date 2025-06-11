package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");
        if (school == null) {
            request.setAttribute("error", "学校情報がありません。");
            return "login.jsp";
        }

        // クラス番号と科目リストは常に取得
        ClassNumDao classdao = new ClassNumDao();
        List<String> classNums = classdao.filter(school);
        SubjectDao subjectdao = new SubjectDao();
        List<Subject> subjectList = subjectdao.filter(school);
        request.setAttribute("classNums", classNums);
        request.setAttribute("subjects", subjectList);

        // 検索条件パラメータ取得
        String entYearParam = request.getParameter("f1");
        String classNum     = request.getParameter("f2");
        String subjectCd    = request.getParameter("f3");
        String no           = request.getParameter("f4");
        // 初期アクセス（パラメータが空）ならエラーにせず表示だけ
        if ((entYearParam == null || entYearParam.isEmpty()) &&
            (classNum      == null || classNum.isEmpty())     &&
            (subjectCd     == null || subjectCd.isEmpty())    &&
            (no            == null || no.isEmpty())) {

            // 初期表示なので学生一覧を出さずにJSPへ
            return "scoremanager/test_regist.jsp";
        }


        // 未入力チェック
        if (entYearParam == null || entYearParam.isEmpty() ||
            classNum      == null || classNum.isEmpty()     ||
            subjectCd     == null || subjectCd.isEmpty()    ||
            no            == null || no.isEmpty()) {

            request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください。");
            return "scoremanager/test_regist.jsp";
        }

        // 学生一覧を取得
        StudentDao studentdao = new StudentDao();
        List<Student> studentList = new ArrayList<>();

        int entYear = Integer.parseInt(entYearParam);
        studentList = studentdao.filter(school, entYear, classNum);
        if (studentList == null || studentList.isEmpty()) {
            request.setAttribute("error", "学生一覧は存在しません。");
            return "scoremanager/test_regist.jsp";
        }

        request.setAttribute("studentList", studentList);
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);
        request.setAttribute("subject", subject);

        request.setAttribute("f1", entYearParam);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        request.setAttribute("f4", no);

        return "scoremanager/test_regist.jsp";
    }
}
