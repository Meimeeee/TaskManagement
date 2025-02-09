<%-- 
    Document   : project
    Created on : Feb 3, 2025, 6:02:57 PM
    Author     : Huynh Han Dong
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
    <%--
    <h1>Welcome, <c:out value="${user.username}" /></h1>

    <c:choose>
        <c:when test="${user.role == 'ProjectManager'}">
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
    --%>
    <!-- Show Project List -->
    <c:choose>
        <c:when test="${not empty projects}">
        <table>
            <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.projectName}</td>
                <td>${project.projectStatus}</td>
                <td>${project.updateAt}</td>
                <%-- Phan quyen theo role
                <c:choose>
                <c:when test="${sessionScope.role == 'ProjectManager'}"> 
                --%> 
                    <td>
                        <form action="edit-project" method="get">
                          <input type="hidden" name="projectId" value="${project.projectId}">
                          <button type="submit">Edit</button>
                        </form>
                    </td> 
                    <td>
                        <form action="delete-project" method="POST">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                <%-- 
                </c:when> 
                </c:choose>
                --%> 
            </tr>
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <h3>${error}</h3>
        </c:otherwise>
    </c:choose>
    <!-- Add Project Button -->
    <%-- Phan quyen theo role
    <c:choose>
    <c:when test="${sessionScope.role == 'ProjectManager'}"> 
    --%>        
    <c:choose>
    <c:when test="${not empty sessionScope.id}">
        <form action="add-project" method="get">
            <button type="submit">Add Project</button>
        </form>
    </c:when>
    <c:otherwise>
        <h3>${error}</h3>
    </c:otherwise>
    </c:choose>
    <%-- 
    <c:/when> 
    <c:/choose>
    --%> 
</body>
</html>
