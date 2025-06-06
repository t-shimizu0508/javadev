<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="index.jsp" %>
<h2>科目情報変更</h2>

<form action="${pageContext.request.contextPath}/scoremanager.main.SubjectUpdateExecute.action" method="post">
    <table border="0" cellpadding="8">
        <tr>
            <th>科目コード</th>
            <td>${subject.cd}</td>
        </tr>
        <tr>
            <th>科目名</th>
            <td>
                <input type="text" name="name" value="${subject.name}" size="20", maxlength="20" required>
                <input type="hidden" name="cd" value="${subject.cd}">
            </td>
        </tr>
    </table>

    <div style="margin-top: 20px;">
        <input type="submit" value="変更">
        <a href="${pageContext.request.contextPath}/scoremanager.main.SubjectList.action">戻る</a>
    </div>
</form>

 <%@ include file="../footer.jsp" %>