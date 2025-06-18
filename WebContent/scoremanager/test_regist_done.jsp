<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="index.jsp" %>
<h2>成績管理</h2>
<p>登録が完了しました。</p>
<a href="<%= request.getContextPath() %>/scoremanager.Manu.action">戻る</a>
 <a href="<%= request.getContextPath() %>/scoremanager.main.TestRegist.action">成績参照</a>
 <%@ include file="../footer.jsp" %>