<%-- 
    Document   : project
    Created on : Feb 3, 2025, 6:02:57 PM
    Author     : Huynh Han Dong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, <c:out value="${user.username}" /></h1>

    <c:choose>
        <c:when test="${user.role == 'PROJECT_MANAGER'}">
            <h2>Project Manager Actions</h2>
            <ul>
                <li><a href="createProject.jsp">Create Project</a></li>
                <li><a href="manageTasks.jsp">Manage Tasks</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <h2>Team Member Actions</h2>
            <ul>
                <li><a href="viewTasks.jsp">View Assigned Tasks</a></li>
                <li><a href="updateTaskStatus.jsp">Update Task Status</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</body>
</html>
