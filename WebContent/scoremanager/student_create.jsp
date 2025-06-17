<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<%@ include file="index.jsp"%>
<h2>学生情報登録</h2>
<form
	action="${pageContext.request.contextPath}/scoremanager.main.StudentCreateExecute.action"
	method="post">
	<!-- 入学年度 -->
	<label>入学年度</label><br> <select name="entYear">
		<option value="">----------</option>
		<%
			java.util.Calendar cal = java.util.Calendar.getInstance();
			int currentYear = cal.get(java.util.Calendar.YEAR);

			for (int y = currentYear - 5; y <= currentYear + 5; y++) {
		%>
		<option value="<%=y%>"><%=y%></option>
		<%
			}
		%>
	</select><br>
	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>
	<br>

	<!-- 学生番号 -->
	<label>学生番号</label><br> <input type="text" name="no"
		maxlength="10" value="${no}" placeholder="学生番号を入力してください" required><br>
	<br>
	<!-- 氏名 -->
	<label>氏名</label><br> <input type="text" name="name"
		maxlength="10" value="${name}" placeholder="氏名を入力してください" required><br>
	<br>

	<!-- クラス -->
	<label>クラス</label><br> <select name="class_num">
		<c:forEach var="classNum" items="${classNums}">
			<option value="${classNum}"
				${classNum == selectedClass ? 'selected' : ''}>${classNum}
			</option>
		</c:forEach>
	</select><br> <br>


	<!-- 登録して終了ボタン -->
	<button type="submit" name="end">登録して終了</button>

</form>
<a
	href="<%=request.getContextPath()%>/scoremanager.main.StudentList.action">戻る</a>
<%@ include file="../footer.jsp"%>