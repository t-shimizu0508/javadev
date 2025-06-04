<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="index.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>学生情報変更</h2>

<c:if test="${not empty error1}">
	<p style="color: red;">${error2}</p>
</c:if>


<form
	action="${pageContext.request.contextPath}/scoremanager.main.StudentUpdateExecute.action"
	method="post">
	<table class="student-form">
		<tr>
			<th>入学年度</th>
			<td><span>${student.entYear}</span> <input type="hidden"
				name="ent_year" value="${student.entYear}"></td>
		</tr>
		<tr>
			<th>学生番号</th>
			<td><span>${student.no}</span> <input type="hidden" name="no"
				value="${student.no}"></td>
		</tr>
		<tr>
			<c:if test="${not empty error2}">
				<p style="color: red;">${error1}</p>
			</c:if>
			<th>氏名</th>
			<td><input type="text" name="name" value="${student.name}"
				maxlength="10" required></td>
		</tr>
		<tr>
			<th>クラス</th>
			<td><select name="class_num">
					<c:forEach var="c" items="${classNumList}">
						<option value="${c}"
							<c:if test="${c == student.classNum}">selected</c:if>>${c}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>在学中</th>
			<td><input type="checkbox" name="is_attend" value="true"
				${student.attend ? "checked" : ""}></td>
		</tr>
	</table>

	<div class="btn-area">
		<input type="submit" value="変更"> <a
			href="${pageContext.request.contextPath}/scoremanager.main.StudentList.action">戻る</a>
	</div>
</form>

<%@ include file="../footer.jsp"%>