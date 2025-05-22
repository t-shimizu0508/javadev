package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.User;
import tool.Action;

public class StudentListAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 HttpSession session=request.getSession();
		 User user = (User) session.getAttribute("user");
	        if (user != null && user.isAuthenticated()) {
	            // 認証されていたら学生情報表示
	        	Teacher teacher = new Teacher();
	        	String code = teacher.getCd();
	        } else {
	            // 未認証ならログインページへ
	            return "login.jsp";
	        }
	        
	        return "";
	 }
}
