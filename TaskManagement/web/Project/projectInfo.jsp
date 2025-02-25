<%-- 
    Document   : projectInfo
    Created on : Feb 10, 2025, 10:45:52 AM
    Author     : Huynh Han Dong
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="CSS/project-info.css"/>
    <title>Project Info</title>
</head>
<body>
    <c:if test="${not empty sessionScope.id}">      
        <form action="project" method="get">
            <button class="go-back-button" type="submit">Go back</button>
        </form>
        <c:choose>
            <c:when test="${not empty project}">
                
                <!-- Show Project Info -->
                <div class="project-info">
                    <h2>About Project</h2>
                    <p><strong>Project name: </strong>${project.projectName}</p>
                    <p><strong>Description: </strong>${project.projectDescription}</p>
                    <p><strong>Status: </strong>${project.projectStatus}</p>
                    <p><strong>Create by: </strong>${createdBy}</p>
                    <p><strong>Create At: </strong><fmt:formatDate value="${project.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                    <p><strong>Update at: </strong><fmt:formatDate value="${project.updateAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>

                    <!-- Show Edit Button -->
                    <c:if test="${sessionScope.role == 'manager'}"> 
                    <form action="edit-project" method="get">
                        <input type="hidden" name="projectId" value="${project.projectId}">
                        <button class="edit-button" type="submit">Edit</button>
                    </form>
                    </c:if> 
                </div>    

                <!-- Show Project Member List -->
                <div class="project-info">
                    <h2>Project member</h2>
                    <c:choose>
                        <c:when test="${not empty members}">
                            <c:forEach var="member" items="${members}">
                            <div class="project-member">
                                <div class="project-content">
                                    <p><strong>${member.username}</strong></p>
                                    <p>${member.role}</p>
                                </div>

                                <!-- Show Delete Member Button -->
                                <c:if test="${sessionScope.role == 'manager'}"> 
                                    <c:if test="${member.role == 'member'}"> 
                                        <form action="delete-member" method="POST" onsubmit="return confirm('Are you sure you want to delete this member?');">
                                            <input type="hidden" name="projectId" value="${project.projectId}">
                                            <input type="hidden" name="accountId" value="${member.accountId}">
                                            <input type="hidden" name="role" value="manager">
                                            <button class="delete-button" type="submit">Delete</button>
                                        </form>
                                    </c:if>
                                </c:if>
                            </div>        
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3>${error}</h3>
                        </c:otherwise>
                    </c:choose>

                    <!--Add Member FORM-->
                    <c:if test="${sessionScope.role == 'manager'}"> 
                    <c:if test="${not empty sessionScope.addError}">
                        <p class="error-message">${sessionScope.addError}</p>
                        <c:remove var="addError" scope="session"/>
                    </c:if>
                    <div class="add-member-form">
                        <form action="add-member" method="post">
                            <label>Username: </label>
                            <input type="text" class="input-field" name="username" required placeholder="Enter username">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <button type="submit">Add Member</button>
                        </form>
                    </div>
                    </c:if>
                </div>
                    
                <c:choose>
                    <c:when test="${sessionScope.role == 'manager'}">

                        <!-- Show Delete Project Button -->
                        <c:if test="${not empty sessionScope.deleteError}">
                            <p class="error-message">${sessionScope.deleteError}</p>
                            <c:remove var="deleteError" scope="session"/>
                        </c:if>
                        <div class="delete-project-container">
                            <form action="delete-project" method="POST" onsubmit="return confirm('Are you sure you want to delete this project?');">
                                <input type="hidden" name="projectId" value="${project.projectId}">
                                <input type="hidden" name="accountId" value="${sessionScope.id}">
                                <button class="delete-project-button" type="submit">Delete Project</button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>

                    <!-- Show Leave Project Button -->
                    <div class="delete-project-container">
                        <form action="delete-member" method="POST" onsubmit="return confirm('Are you sure you want to leave this project?');">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <input type="hidden" name="accountId" value="${sessionScope.id}">
                            <input type="hidden" name="role" value="member">
                            <button class="delete-project-button" type="submit">Leave Project</button>
                        </form>
                    </div>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h3>${error}</h3>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
