<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>科目情報登録</h2>
 <p>登録が完了しました。</p>
 <a href="<%= request.getContextPath() %>/scoremanager.Manu.action">戻る</a>
 <a href="<%= request.getContextPath() %>/scoremanager.main.SubjectList.action">科目一覧</a>
 <%@ include file="../footer.jsp" %>