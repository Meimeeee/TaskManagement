<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width-device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/Signup.css">
    </head>
    <body>
        <c:set var="errors" value="${requestScope.errors}" />
        <h2 style="text-align: center;">Sign Up</h2>
        <form action="${pageContext.request.contextPath}/signup-servlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="${param.username}" required>
            <c:if test="${not empty errors.username}">
                <p style="color: red; font-size: 15px">${errors.username}</p>
            </c:if>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="${param.password}" required>

            <c:if test="${not empty errors.password}">
                <p style="color: red; font-size: 15px">${errors.password}</p>
            </c:if>

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

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${param.email}" required>
            <c:if test="${not empty errors.email}">
                <p style="color: red; font-size: 15px">${errors.email}</p>
            </c:if>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="${param.firstName}" required>
            <c:if test="${not empty errors.firstName}">
                <p style="color: red; font-size: 15px">${errors.firstName}</p>
            </c:if>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="${param.lastName}" required>
            <c:if test="${not empty errors.lastName}">
                <p style="color: red; font-size: 15px">${errors.lastName}</p>
            </c:if>

            <label for="phone">Phone Number:</label>
            <input type="text" id="phone" name="phone" value="${param.phone}" required>
            <c:if test="${not empty errors.phoneNumber}">
                <p style="color: red; font-size: 15px">${errors.phoneNumber}</p>
            </c:if>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    let dobInput = document.getElementById("dob");
                    let dobError = document.getElementById("dobError");

                    let minDate = new Date("1990-01-01");
                    let maxDate = new Date();

                    dobInput.setAttribute("min", minDate.toISOString().split("T")[0]);
                    dobInput.setAttribute("max", maxDate.toISOString().split("T")[0]);

                    dobInput.addEventListener("change", function () {
                        let dobValue = new Date(this.value);

                        if (dobValue < minDate || dobValue > maxDate || isNaN(dobValue.getTime())) {
                            dobError.textContent = "Invalid date.";
                            this.value = "";
                        } else {
                            dobError.textContent = "";
                        }
                    });
                });
            </script>

            <button type="submit">SIGN UP</button>

        </form>
    </body>
</html>


