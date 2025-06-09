<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="index.jsp"%>
<h2>科目情報登録</h2>
<form
	action="${pageContext.request.contextPath}/scoremanager.main.SubjectCreateExecute.action"
	method="post">
	<!-- 科目コード -->
	<c:if test="${not empty error}">
    	<p style="color: red;">${error}</p>
	</c:if>
	<label for="cd">科目コード</label><br /> <input type="text" id="cd"
		name="cd" value="${cd}" maxlength="20" required
		placeholder="科目コードを入力してください" /><br />
	<br />

	<!-- 科目名 -->
	<label for="name">科目名</label><br /> <input type="text" id="name"
		name="name" value="${name}" maxlength="20" required
		placeholder="科目名を入力してください" /><br />
	<br />

	<!-- 登録ボタン -->
	<input type="submit" value="登録" />

</form>

<!-- 戻るリンク -->
<p>
	<a
		href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
</p>
<%@ include file="../footer.jsp"%>