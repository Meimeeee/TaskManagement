<%-- 
    Document   : editProject
    Created on : Feb 9, 2025, 12:24:14 AM
    Author     : Huynh Han Dong
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Project</title>
</head>
<body>
    <c:if test="${not empty sessionScope.id}">
        <c:choose>
            <c:when test="${not empty project}">
                <h2><c:out value="${project.projectName}"/></h2>
                <form action="edit-project" method="post">
                    <fieldset>
                        <label>Project name:</label>
                        <input type="text" name="projectName" value="${project.projectName}"/><br><br>

                        <label>Project description:</label>
                        <textarea name="description">${project.projectDescription}</textarea><br><br>

                        <label>Project status:</label>
                        <label><input type="radio" name="status" value="InProgress" ${project.projectStatus == 'InProgress' ? 'checked' : ''}> InProgress</label>
                        <label><input type="radio" name="status" value="Done" ${project.projectStatus == 'Done' ? 'checked' : ''}> Done</label>

                        <input type="hidden" name="projectId" value="${project.projectId}"/>
                        <input type="hidden" name="accountId" value="${project.createBy}"/>
                        <input type="hidden" name="createAt" value="${project.createAt}"/>

                        <button type="submit">Save</button>
                    </fieldset>
                </form>
            </c:when>
            <c:otherwise>
                <h3>${error}</h3>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
