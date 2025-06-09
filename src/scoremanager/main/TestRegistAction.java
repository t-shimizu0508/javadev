package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 HttpSession session = request.getSession();
	        School school = (School) session.getAttribute("school");
	        if (school == null) {
	            request.setAttribute("error", "学校情報がありません。");
	            return "login.jsp";
	        }

	        ClassNumDao classdao = new ClassNumDao();
	        List<String> classNums = classdao.filter(school);
	        SubjectDao subjectdao = new SubjectDao();
	        List<Subject> subjectList = subjectdao.filter(school);
	        request.setAttribute("classNums", classNums);
	        request.setAttribute("subjects", subjectList);


	        return "scoremanager/test_regist.jsp";
	 }
}
