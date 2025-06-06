<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../header.jsp" %>
 <%@ include file="index.jsp" %>
 <h2>科目情報削除</h2>
 <p>削除が完了しました。</p>
 <a href="<%= request.getContextPath() %>/scoremanager.main.SubjectList.action">科目一覧</a>
 <%@ include file="../footer.jsp" %>