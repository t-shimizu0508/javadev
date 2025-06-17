package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
		HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");
        if (school == null) {
            request.setAttribute("error", "学校情報がありません。");
            return "login.jsp";
        }


        ClassNumDao classdao = new ClassNumDao();
        List<String> classNums = classdao.filter(school);
        request.setAttribute("classNums", classNums);
		return "scoremanager/student_create.jsp";
	}

}
