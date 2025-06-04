<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>学生情報変更</h2>
 <p>変更が完了しました。</p>
 <a href="<%= request.getContextPath() %>/scoremanager.main.StudentList.action">学生一覧</a>
 <%@ include file="../footer.jsp" %>