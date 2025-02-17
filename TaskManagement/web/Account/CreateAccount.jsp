<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Account</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/CreateAccount.css">
</head>
<body>
    <div class="container">
        <h2>Create Account</h2>
        <c:set var="errors" value="${requestScope.errors}" />
        <form action="<%= request.getContextPath()%>/create-account" method="POST">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" value="${param.username}" required>
            <c:if test="${not empty errors.username}">
                <p style="color: red; font-size: 15px">${errors.username}</p>
            </c:if>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" value="${param.password}" required>
            <c:if test="${not empty errors.password}">
                <p style="color: red; font-size: 15px">${errors.password}</p>
            </c:if>
            
            <div class="buttons">
                <button type="submit" class="save-btn">Save Changes</button>
                <button type="button" class="back-btn" onclick="window.location.href='<%= request.getContextPath()%>/project'">Back</button>
            </div>
        </form>
    </div>
</body>
</html>
