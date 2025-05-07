<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="header.jsp" flush="true" />

    <form action="LoginServlet" method="post">
        <label for="loginId">ログインID:</label>
        <input type="text" id="loginId" name="loginId" required /><br/>

        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required />
        <input type="checkbox" id="showPassword" onclick="togglePassword()" />
        <label for="showPassword">パスワードを表示</label><br/>

        <input type="submit" value="ログイン" />
    </form>

    <script>
        function togglePassword() {
            var passwordField = document.getElementById("password");
            if (passwordField.type === "password") {
                passwordField.type = "text";
            } else {
                passwordField.type = "password";
            }
        }
    </script>

    <jsp:include page="footer.jsp" flush="true" />

