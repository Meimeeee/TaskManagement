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
    <link rel="stylesheet" type="text/css" href="CSS/add-project.css"/>
    <title>Edit Project</title>
</head>
<body>
    <c:if test="${not empty sessionScope.id}">
        <form action="project-info" method="get">
            <input type="hidden" name="projectId" value="${project.projectId}"/>
            <button class="go-back-button" type="submit">Go back</button>
        </form>
        <c:choose>
            <c:when test="${not empty project}">
                <div class="container">
                    <h2>Edit project <c:out value="${project.projectName}"/></h2>
                    <form action="edit-project" method="post">
                        <div class="info">
                            <label><strong>Project name</strong></label>
                        </div>    
                        <div class="info">        
                            <input type="text" class="input-field" name="projectName" value="${project.projectName}"/>
                        </div>    
                        <div class="info">           
                            <label><strong>Description</strong></label>
                        </div>    
                        <div class="info">           
                            <textarea class="input-field" name="description">${project.projectDescription}</textarea>
                        </div>    
                        <div class="info">   
                            <label><strong>Status</strong></label>
                        </div>    
                        <div class="info">           
                            <label><input type="radio" name="status" value="InProgress" ${project.projectStatus == 'InProgress' ? 'checked' : ''}> In Progress</label>
                        </div>    
                        <div class="info">           
                            <label><input type="radio" name="status" value="Done" ${project.projectStatus == 'Done' ? 'checked' : ''}> Done</label>
                        </div>
                        <div class="info">  
                            <input type="hidden" name="projectId" value="${project.projectId}"/>
                            <input type="hidden" name="accountId" value="${project.createBy}"/>
                            <input type="hidden" name="createAt" value="${project.createAt}"/>  
                        </div>
                        <div class="info">   
                            <button class="save-button" type="submit">Save</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <h3>${error}</h3>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
