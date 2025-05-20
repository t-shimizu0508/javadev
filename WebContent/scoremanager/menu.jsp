<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>メニュー</h2>
 <div class="menu-container_2">
	<ul>
	<li><a href="<%= request.getContextPath() %>/scoremanager/menu.jsp">メニュー</a></li>
	<li><a href="<%= request.getContextPath() %>/scoremanager/student_list.jsp">学生管理</a></li>
	<li>成績管理</li>
	<li><a href="<%= request.getContextPath() %>/scoremanager/test_regist.jsp">成績登録</a></li>
	<li><a href="<%= request.getContextPath() %>/scoremanager/test_list.jsp">成績参照</a></li>
	<li><a href="<%= request.getContextPath() %>/scoremanager/subject_list.jsp">科目管理</a></li>
	</ul>
</div>
 <%@ include file="../footer.jsp" %>