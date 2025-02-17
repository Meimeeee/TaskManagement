<%-- 
    Document   : SignUp
    Created on : Jan 31, 2025, 3:57:45 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width-device-width, initial-scale=1.0">
        <title>Log In</title>
        <link rel="stylesheet" href="CSS/Login.css">
    </head>
    <body>
        <div class="login-container">
            <h2>LOG IN</h2>
            <c:set var="errors" value="${requestScope.errors}" />
            <form action="login-servlet" method="POST">
                <div class="input-group">
                    <label for="Username">Username</label>
                    <input type="text" id="username" name="username" value="${param.username}" required> 
                    <c:if test="${not empty errors.username}">
                        <p style="color: red; font-size: 15px">${errors.username}</p>
                    </c:if>
                </div>

                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" value="${param.password}" required>
                    <c:if test="${not empty errors.password}">
                        <p style="color: red; font-size: 15px">${errors.password}</p>
                    </c:if>
                </div>

                <button type="submit" class="login-btn">LOG IN</button>
            </form>

            <div class="signup-link">
                <p>Do you have an account? <a href="Identity/signup.jsp">Sign up here</a></p>
            </div>
        </div>

        <script>
                document.addEventListener("DOMContentLoaded", function () {
                    let passwordInput = document.getElementById("password");
                    let showPasswordCheckbox = document.getElementById("showPassword");

                    // Kiểm tra nếu checkbox đã được chọn trước đó
                    if (localStorage.getItem("showPassword") === "true") {
                        showPasswordCheckbox.checked = true;
                        passwordInput.type = "text";
                    }

                    // Thêm sự kiện thay đổi trạng thái checkbox
                    showPasswordCheckbox.addEventListener("change", function () {
                        if (this.checked) {
                            passwordInput.type = "text";
                            localStorage.setItem("showPassword", "true");
                        } else {
                            passwordInput.type = "password";
                            localStorage.setItem("showPassword", "false");
                        }
                    });
                });
            </script>
    </body>
</html>
