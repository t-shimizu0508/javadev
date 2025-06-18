package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		School school = (School) session.getAttribute("school");
		if (school == null) {
			request.setAttribute("error", "学校情報がありません。");
			return "login.jsp";
		}

		String subjectCd = request.getParameter("subject");
		String countStr = request.getParameter("count");

		int count = Integer.parseInt(countStr);
		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(subjectCd, school);

		String[] studentNos = request.getParameterValues("regist");
		List<Test> testList = new ArrayList<>();

		for (String stuNo : studentNos) {
			String pointStr = request.getParameter("point_" + stuNo);

			// 点数が未入力ならスキップ
			if (pointStr == null || pointStr.trim().isEmpty())
				continue;

			int point;
			try {
				point = Integer.parseInt(pointStr.trim());

				if (point < 0 || point > 100) {
					request.setAttribute("error", "点数は0から100の間で入力してください。");
					return "scoremanager.main.TestRegist.action";
				}

			} catch (NumberFormatException e) {
				request.setAttribute("error", "数値しか入力できません。");
				return "scoremanager.main.TestRegist.action";
			}

			// Test オブジェクト作成
			Test t = new Test();

			Student stu = new Student();
			stu.setNo(stuNo);
			t.setStudent(stu);

			t.setSubject(subject);
			t.setSchool(school);
			t.setNo(count);
			t.setPoint(point);
			t.setClassNum(request.getParameter("class_num"));
			testList.add(t);
		}

		TestDao tDao = new TestDao();
		tDao.save(testList);

		return "scoremanager/test_regist_done.jsp";
	}
}
