<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../header.jsp" %>

    <form action="scoremanager.LoginExecute.action" method="post">
    	<h2>ログイン</h2>
        <label for="loginId">ログインID:</label>
        <input type="text" id="loginId" name="loginId" required /><br/>

        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required />
        <input type="checkbox" id="showPassword" onclick="togglePassword()" />
        <label for="showPassword">パスワードを表示</label><br/>

        <input type="submit" value="ログイン" />
    </form>


    <%@ include file="../footer.jsp" %>

