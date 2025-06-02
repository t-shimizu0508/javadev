<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.Student"%>
<%@ page import="java.util.*, bean.Student"%>
<%@ include file="../header.jsp"%>
<%@ include file="index.jsp"%>
<h2>学生管理</h2>
<a href="<%= request.getContextPath() %>/scoremanager/student_create.jsp">新規登録</a>
<form action="<%= request.getContextPath() %>/scoremanager/main/StudentList.action" method="get">
	<c:if test="${not empty error}">
   	 	<p style="color: red;">${error}</p>
	</c:if>
	入学年度： <select name="entYear">
		<option value="">----</option>
		<%
    		    java.util.Calendar cal = java.util.Calendar.getInstance();
        		int currentYear = cal.get(java.util.Calendar.YEAR);

        		for (int y = currentYear - 5; y <= currentYear + 5; y++) {
  			%>
		<option value="<%= y %>"><%= y %></option>
		<%
    		    }
    		%>
	</select> クラス： <select name="classNum">
		<option value="">----</option>
		<%
        List<Student> list = (List<Student>) request.getAttribute("studentList");
        java.util.Set<String> classSet = new java.util.TreeSet<>(); // ソートもしたいなら TreeSet

        if (list != null) {
            for (Student s : list) {
                if (s.getClassNum() != null && !s.getClassNum().isEmpty()) {
                    classSet.add(s.getClassNum());
                }
            }
        }

        for (String classNumOption : classSet) {
    %>
		<option value="<%= classNumOption %>"><%= classNumOption %></option>
		<%
        }
    %>
	</select> 在学中：<input type="checkbox" name="isAttend" value="true" /> <input
		type="submit" value="絞り込み" />
</form>
<%
    if (list != null && !list.isEmpty()) {

%>
<p>
	検索ヒット件数：<strong><%= list.size() %></strong> 件
</p>
<table border="1">
	<tr>
		<th>入学年度</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>クラス</th>
		<th>在学中</th>
		<th>操作</th>
	</tr>
	<%
            for (Student s : list) {
        %>
	<tr>
		<td><%= s.getEntYear() %></td>
		<td><%= s.getNo() %></td>
		<td><%= s.getName() %></td>
		<td><%= s.getClassNum() %></td>
		<td><%= s.isAttend() ? "○" : "×" %></td>
		<td><a
			href="<%= request.getContextPath() %>/scoremanager/student_update.jsp">変更</a></td>
	</tr>
	<%
            }
        %>
</table>
<%
    } else {
%>
<p>学生情報が存在しませんでした。</p>
<%
    }
%>
<%@ include file="../footer.jsp"%>