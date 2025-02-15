<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>CREATE MANAGER ACCOUNT</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/CreateManager.css">
    </head>
    <body>
        <div class="container">
            <c:set var="errors" value="${requestScope.errors}" />
            <h2>CREATE MANAGER ACCOUNT</h2>
            <form action="<%= request.getContextPath()%>/create-manager" method="post">
                <div class="form-group">
                    <label for="username">Username :</label>
                    <input type="text" id="username" name="username" value="${param.username}"required>
                    <c:if test="${not empty errors.username}">
                        <p style="color: red; font-size: 15px">${errors.username}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="password">Password :</label>
                    <input type="password" id="password" name="password" value="${param.password}"required>
                    <c:if test="${not empty errors.password}">
                        <p style="color: red; font-size: 15px">${errors.password}</p>
                    </c:if>
                </div>
                <button type="submit" class="btn">CREATE</button>
            </form>
                    <button class="btn-back" onclick="window.location.href = '${pageContext.request.contextPath}/project'">Back to Home</button>
        </body>
                </html>
