<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width-device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" href="CSS/Signup.css">
    </head>
    <body>
        <c:set var="errors" value="${requestScope.errors}" />
        <h2 style="text-align: center;">Sign Up</h2>
        <form action="signup-servlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <c:if test="${not empty errors.email}">
                <p style="color: red">${errors.email}</p>
            </c:if>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
            <c:if test="${not empty errors.firstName}">
                <p style="color: red">${errors.firstName}</p>
            </c:if>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
            <c:if test="${not empty errors.lastName}">
                <p style="color: red">${errors.lastName}</p>
            </c:if>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required>
            <c:if test="${not empty errors.phoneNumber}">
                <p style="color: red">${errors.phoneNumber}</p>
            </c:if>

            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" required>

            <button type="submit">Sign Up</button>



        </form>
    </body>
</html>


