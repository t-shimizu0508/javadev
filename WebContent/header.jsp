<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="bean.Teacher" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

</head>
<body>
<div class="header-wrapper">
    <h1>得点管理システム</h1>
    <%
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher != null) {
    %>
        <span><%= teacher.getName() %></span>
        <a href="<%= request.getContextPath() %>/scoremanager.main.Logout.action">ログアウト</a>
    <%
        } else {
    %>
        <span>ログインしていません</span>
    <%
        }
    %>

</div>
