<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.Calendar, java.util.ArrayList, java.util.List"%>
<%@ include file="../header.jsp"%>
<%@ include file="index.jsp"%>
<h2>成績管理</h2>
<form
	action="${pageContext.request.contextPath}/scoremanager/main/TestSearch.action"
	method="get">
	<%
    Calendar cal = Calendar.getInstance();
    int currentYear = cal.get(Calendar.YEAR);
    List<Integer> entYears = new ArrayList<>();
    for(int i = currentYear - 5; i <= currentYear + 5; i++) {
        entYears.add(i);
    }
    request.setAttribute("entYears", entYears);
%>

	<label for="f1">入学年度：</label> <select id="f1" name="f1">
		<option value="">--------</option>
		<c:forEach var="year" items="${entYears}">
			<option value="${year}">${year}</option>
		</c:forEach>
	</select>
	<!-- クラス -->
	<label for="f2">クラス：</label> <select id="f2" name="f2">
		<option value="">--------</option>
		<c:forEach var="cn" items="${classNums}">
			<option value="${cn}">${cn}</option>
		</c:forEach>
	</select>

	<!-- 科目 -->
	<label for="f3">科目：</label> <select id="f3" name="f3">
		<option value="">--------</option>
		<c:forEach var="subject" items="${subjects}">
			<option value="${subject.cd}">${subject.name}</option>
		</c:forEach>
	</select>

	<!-- 回数 -->
	<label for="f4">回数：</label> <select id="f4" name="f4">
		<option value="">--------</option>
		<c:forEach var="i" begin="1" end="10">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>

	<!-- 検索ボタン -->
	<button type="submit">検索</button>
</form>
<%@ include file="../footer.jsp"%>