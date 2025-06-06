<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="index.jsp" %>
<h2>科目情報変更</h2>
<p>変更が完了しました。</p>
<a href="<%= request.getContextPath() %>/scoremanager.main.SubjectList.action">科目一覧</a>

 <%@ include file="../footer.jsp" %>