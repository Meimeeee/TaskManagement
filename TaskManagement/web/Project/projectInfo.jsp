<%-- 
    Document   : projectInfo
    Created on : Feb 10, 2025, 10:45:52 AM
    Author     : Huynh Han Dong
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Info</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty project}">
                <!-- Show Project List -->
                <p>Project name: <c:out value="${project.projectName}"/></p>
                <p>Description: <c:out value="${project.projectDescription}"/></p>
                <p>Status: <c:out value="${project.projectStatus}"/></p>
                <p>Create by: <c:out value="${project.createBy}"/></p>  <%-- Doi lai thanh ${requestScope.createdBy}--%>
                <p>Create At: <c:out value="${project.createAt}"/></p>
                <p>Update at: <c:out value="${project.updateAt}"/></p>
                
                <!-- Show Edit And Delete Button -->
                <c:choose>
                <c:when test="${sessionScope.role == 'ProjectManager'}"> 
                        <form action="edit-project" method="get">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <button type="submit">Edit</button>
                        </form>
                        <form action="delete-project" method="POST">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <button type="submit">Delete</button>
                        </form>
                </c:when> 
                </c:choose>
        </c:when>
        <c:otherwise>
            <h3>${error}</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
