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
    <link rel="stylesheet" type="text/css" href="CSS/Project.css"/>
    <title>Project Info</title>
</head>
<body>
    <c:choose>
        <c:when test="${not empty project}">
            <!-- Show Project Info -->
            <div class="project-info">
                <h2>About Project</h2>
                <p><strong>Project name: </strong><c:out value="${project.projectName}"/></p>
                <p><strong>Description: </strong><c:out value="${project.projectDescription}"/></p>
                <p><strong>Status: </strong><c:out value="${project.projectStatus}"/></p>
                <p><strong>Create by: </strong><c:out value="${project.createBy}"/></p>  <%-- Doi lai thanh ${requestScope.createdBy}--%>
                <p><strong>Create At: </strong><c:out value="${project.createAt}"/></p>
                <p><strong>Update at: </strong><c:out value="${project.updateAt}"/></p>
                
                <!-- Show Edit And Delete Button -->
                <c:choose>
                    <c:when test="${sessionScope.role == 'manager'}"> 
                            <form action="edit-project" method="get">
                                <input type="hidden" name="projectId" value="${project.projectId}">
                                <button type="submit">Edit</button>
                            </form>
                    </c:when> 
                </c:choose>
            </div>    
            
            <!-- Show Project Member List -->
            <c:choose>
                <c:when test="${not empty members}">
                    <div class="project-info">
                        <h2>Project member</h2>
                        <c:forEach var="member" items="${members}">
                            <div class="project-member">
                                    <div class="project-content">
                                        <p><strong>${member.username}</strong></p>
                                        <p>${member.role}</p>
                                    </div>
                                    <div class="delete-button">
                                        <c:choose>
                                            <c:when test="${sessionScope.role == 'manager'}"> 
                                                <form action="delete-member" method="POST">
                                                    <input type="hidden" name="projectId" value="${project.projectId}">
                                                    <input type="hidden" name="acountId" value="${member.accountId}">
                                                    <button type="submit">Delete</button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                    </div>
                            </div>        
                        </c:forEach>

                            <!-- Show Add And Delete Button -->
                            <c:choose>
                               <c:when test="${sessionScope.role == 'manager'}"> 
                                    <form action="add-member" method="get">
                                        <input type="hidden" name="projectId" value="${project.projectId}">
                                        <input type="hidden" name="acountId" value="${member.accountId}">
                                        <button type="submit">Add</button>
                                    </form>
                                </c:when>
                            </c:choose>
                    </div>
                </c:when>
                <c:otherwise>
                    <h3>${error}</h3>
                </c:otherwise>
            </c:choose>
            <div class="delete-project-container">
                <form action="delete-project" method="POST">
                    <input type="hidden" name="projectId" value="${project.projectId}">
                    <button class="delete-project-button" type="submit">Delete Project</button>
            </form>
            </div>
        </c:when>
        <c:otherwise>
            <h3>${error}</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
