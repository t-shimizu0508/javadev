<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="index.jsp" %>
 <h2>科目削除確認</h2>

    <p>
        「${subject.name}（${subject.cd})」を削除してもよろしいですか？
    </p>

    <form action="${pageContext.request.contextPath}/scoremanager/main/SubjectDeleteExecute.action" method="post">
        <input type="hidden" name="subject_cd" value="${subject.cd}">
        <input type="hidden" name="subject_name" value="${subject.name}">
        <input type="submit" value="削除">
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
    </p>
 <%@ include file="../footer.jsp" %>