package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.User;
import tool.Action;

public class StudentListAction extends Action{
	 public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		 HttpSession session=request.getSession();
		 User user = (User) session.getAttribute("user");
	        if (user != null && user.isAuthenticated()) {
	            // 認証されていたら学生情報表示
	        	Student student=new Student();
	        	if (student!=null) {
//	        		 List<Student> studentList = postFilter;
//	                 request.setAttribute("studentList", studentList);
	    			return "scoremanager/student_list.jsp";
	        	}


	        }
	     // 未認証ならログインページへ
            return "login.jsp";
	 }
}