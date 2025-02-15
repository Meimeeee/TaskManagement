<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Account</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/EditAccount.css">

    </head>
    <body>

        <div class="container">
            <c:set var="errors" value="${requestScope.errors}" />
            <h2 style="color: #1b5e20;">Edit Account</h2>
            <form action="<%= request.getContextPath()%>/update-account" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" value="${requestScope.username}" required>
                    <c:if test="${not empty errors.username}">
                        <p style="color: red; font-size: 15px">${errors.username}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="${requestScope.password}" required>
                    <c:if test="${not empty errors.password}">
                        <p style="color: red; font-size: 15px">${errors.password}</p>
                    </c:if>
                </div>
                <button type="submit" class="btn-submit">Update</button>
            </form>
            <button class="btn-back" onclick="window.location.href = '${pageContext.request.contextPath}/project'">Back to Home</button>
        </div>

    </body>
</html>
