<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.Student" %>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>学生管理</h2>
<%
    Student student = (Student) session.getAttribute("student");
    if (student != null) {
%>
    <table border="1">
        <tr><th>入学年度</th><td><%= student.getEntYear() %></td></tr>
        <tr><th>学籍番号</th><td><%= student.getNo() %></td></tr>
        <tr><th>名前</th><td><%= student.getName() %></td></tr>
    </table>
<%
    } else {
%>
    <p>学生情報が見つかりませんでした。</p>
<%
    }
%>
 <%@ include file="../footer.jsp" %>