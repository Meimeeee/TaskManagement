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
    <link rel="stylesheet" type="text/css" href="CSS/add-project.css"/>
    <title>Add Project</title>
</head>
<body>
    <c:if test="${not empty sessionScope.id}">
        <form action="project" method="get">
            <button class="go-back-button" type="submit">Go back</button>
        </form>
        
        <div class="container">
            <h2>Add Project</h2>
            <form action="add-project" method="post">
                <div class="info">
                    <label><strong>Project name</strong></label>
                </div>
                <div class="info">
                    <input type="text" class="input-field" name="projectName" required placeholder="Enter project name"><br>
                </div>
                <div class="info">
                   <label><strong>Description</strong></label>
                </div>
                <div class="info">
                    <textarea class="input-field" name="description" placeholder="Write some description"></textarea><br>
                </div>
                <input type="hidden" name="createBy" value="${sessionScope.id}">
                <div class="info">
                <button class="save-button" type="submit">Create</button>
                </div>
            </form>
        </div>
    </c:if>
</body>
</html>

