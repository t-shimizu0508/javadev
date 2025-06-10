<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.Calendar, java.util.ArrayList, java.util.List"%>
<%@ include file="../header.jsp"%>
<%@ include file="index.jsp"%>
<h2>成績管理</h2>
<form
	action="${pageContext.request.contextPath}/scoremanager.main.TestRegist.action"
	method="get">
	<%
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		List<Integer> entYears = new ArrayList<>();
		for (int i = currentYear - 5; i <= currentYear + 5; i++) {
			entYears.add(i);
		}
		request.setAttribute("entYears", entYears);
	%>
	<c:if test="${not empty error}">
		<p style="color: red">${error}</p>
	</c:if>
	<label for="f1">入学年度：</label> <select id="f1" name="f1">
		<option value="">--------</option>
		<c:forEach var="year" items="${entYears}">
			<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
		</c:forEach>
	</select>
	<!-- クラス -->
	<label for="f2">クラス：</label> <select id="f2" name="f2">
		<option value="">--------</option>
		<c:forEach var="cn" items="${classNums}">
			<option value="${cn}" <c:if test="${cn == f2}">selected</c:if>>${cn}</option>
		</c:forEach>
	</select>

	<!-- 科目 -->
	<label for="f3">科目：</label> <select id="f3" name="f3">
		<option value="">--------</option>
		<c:forEach var="subject" items="${subjects}">
			<option value="${subject.cd}"
				<c:if test="${subject.cd == f3}">selected</c:if>>
				${subject.name}</option>
		</c:forEach>
	</select>


	<!-- 回数 -->
	<label for="f4">回数：</label> <select id="f4" name="f4">
		<option value="">--------</option>
		<c:forEach var="i" begin="1" end="10">
			<option value="${i}" <c:if test="${i == f4}">selected</c:if>>
				${i}</option>
		</c:forEach>
	</select>


	<!-- 検索ボタン -->
	<button type="submit">検索</button>
</form>
<c:if test="${not empty studentList}">
	<hr>
	<!-- 選択科目と回数の表示 -->
	<c:if test="${not empty f3 and not empty f4}">
		<p>
			科目：<strong>${subject.getName()}</strong> 回数：<strong>${f4}</strong>回
		</p>
	</c:if>
	<c:if test="${not empty error}">
		<p style="color: red">${error}</p>
	</c:if>

	<form
		action="${pageContext.request.contextPath}/scoremanager.main.TestRegistExecute.action"
		method="post" onsubmit="return validateForm();">
		<table border="1" cellpadding="5" cellspacing="0">
			<thead>
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>点数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="stu" items="${studentList}">
					<tr>
						<td>${stu.entYear}</td>
						<td>${stu.classNum}</td>
						<td>${stu.no}</td>
						<td>${stu.name}</td>
						<td><input type="text" name="point_${stu.no}" value="" /> <input
							type="hidden" name="regist" value="${stu.no}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- 共通 Hidden -->
		<input type="hidden" name="subject" value="${f3}" /> <input
			type="hidden" name="count" value="${f4}" />

		<!-- 登録ボタン -->
		<input type="submit" value="登録して終了" />
	</form>
</c:if>

<%@ include file="../footer.jsp"%>
