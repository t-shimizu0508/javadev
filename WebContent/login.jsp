<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<div class="login-wrapper">
<form action="scoremanager.LoginExecute.action" method="post" onsubmit="return validateForm()">
<h2>ログイン</h2>
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
    <% } %>

    <label for="loginId">ログインID:</label>
    <input type="text" id="loginId" name="loginId" required /><br/>
    <p id="loginIdError" style="color: yellow; display: none;">このフィールドに入力してください。</p>

    <label for="password">パスワード:</label>
    <input type="password" id="password" name="password" required />
    <input type="checkbox" id="showPassword" onclick="togglePassword()" />
    <label for="showPassword">パスワードを表示</label><br/>
    <p id="passwordError" style="color: yellow; display: none;">このフィールドに入力してください。</p>

    <input type="submit" value="ログイン" />

    <script>
    function togglePassword() {
        const passwordField = document.getElementById("password");
        const isChecked = document.getElementById("showPassword").checked;
        passwordField.type = isChecked ? "text" : "password";
    }

    function validateForm() {
        let isValid = true;

        // ログインIDの検証
        const loginId = document.getElementById("loginId").value;
        const loginIdError = document.getElementById("loginIdError");
        if (loginId === "") {
            loginIdError.style.display = "block";
            isValid = false;
        } else {
            loginIdError.style.display = "none";
        }

        // パスワードの検証
        const password = document.getElementById("password").value;
        const passwordError = document.getElementById("passwordError");
        if (password === "") {
            passwordError.style.display = "block";
            isValid = false;
        } else {
            passwordError.style.display = "none";
        }

        return isValid;
    }
    </script>
</form>
</div>

<%@ include file="../footer.jsp" %>
