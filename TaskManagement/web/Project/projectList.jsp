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
    <%-- <h1>Welcome, <c:out value="${sessionScope.username}" /></h1> --%>

    <!-- Add Project Button -->
    <c:choose>
        <c:when test="${sessionScope.role == 'ProjectManager'}">
            <form action="add-project" method="get">
                <button type="submit">Add project</button>
            </form>
        </c:when>
    </c:choose>
    <!-- Show Project List -->
    <c:choose>
        <c:when test="${not empty projects}">
        <table>
            <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.projectName}</td>
                <td>Status: ${project.projectStatus}</td>
                <td>Last Update: ${project.updateAt}</td>
                <td>
                    <form action="project-info" method="get">
                        <input type="hidden" name="projectId" value="${project.projectId}">
                    <button type="submit">Info</button>
                    </form>
                </td>
                <td>
                    <form action="task" method="get">
                        <input type="hidden" name="projectId" value="${project.projectId}">
                    <button type="submit">Go to task</button>
                    </form>
                </td>
            </tr>
            
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <h3>${error}</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
