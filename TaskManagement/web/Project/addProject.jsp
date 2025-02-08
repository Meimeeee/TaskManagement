<%-- 
    Document   : addProject
    Created on : Feb 8, 2025, 5:59:05 PM
    Author     : Huynh Han Dong
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Project</title>
</head>
<body>
    <h2>Add Project</h2>
    <c:choose>
        <c:when test="${not empty sessionScope.id}">
            <form action="add-project" method="post">
                <label>Project name:</label>
                <input type="text" name="projectName" required><br>
                <label>Project description:</label>
                <textarea name="projectDescription"></textarea><br>
                <input type="hidden" name="createBy" value="${requestScope.id}">
                <button type="submit">Create</button>
            </form>
        </c:when>
        <c:otherwise>
            <h3>${error}</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>

