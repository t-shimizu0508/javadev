<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ include file="index.jsp" %>
 <h2>科目管理</h2>

<div class="btn-area" style="text-align: right; margin-bottom: 10px;">
    <a href="${pageContext.request.contextPath}/scoremanager.main.SubjectCreate.action">新規登録</a>
</div>

<table border="1" cellpadding="8" cellspacing="0" style="width: 100%; border-collapse: collapse;">
    <tr style="background-color: #e0e0e0;">
        <th>科目コード</th>
        <th>科目名</th>
        <th colspan="2">操作</th>
    </tr>

    <c:forEach var="s" items="${subjectList}">
        <tr>
            <td>${s.cd}</td>
            <td>${s.name}</td>
            <td><a href="${pageContext.request.contextPath}/scoremanager.main.SubjectUpdate.action?cd=${s.cd}">変更</a></td>
            <td><a href="${pageContext.request.contextPath}/scoremanager.main.SubjectDelete.action?cd=${s.cd}">削除</a></td>
        </tr>
    </c:forEach>
</table>

<div class="btn-area" style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/scoremanager/menu.jsp">メニューに戻る</a>
</div>
</table>
 <%@ include file="../footer.jsp" %>