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
        <link rel="stylesheet" type="text/css" href="CSS/project-list.css"/>
        <title>Home Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.id}">
            <div class="container">
                <div class="sidebar">
                    <p><b>PROFILE</b></p>
                    <ul>
                        <li><a href="<%= request.getContextPath()%>/Account/EditAccount.jsp">Edit Account</a></li>
                        <li><a href="<%= request.getContextPath()%>/Profile/EditProfile.jsp">Edit Profile</a></li>
                        <li><a href="<%= request.getContextPath()%>/show-profile">Show Profile</a></li>
                            <c:if test="${sessionScope.role eq 'admin'}">
                            <li><a href="<%= request.getContextPath()%>/Account/CreateAccount.jsp">Create Manage Account</a></li>
                            </c:if>
                    </ul>
                </div>

                <div class="main-content">
                    <h2>Welcome, <c:out value="${sessionScope.username}"/>!</h2>

                    <!-- Add Project Button -->
                    <c:if test="${sessionScope.role == 'manager'}">
                        <form action="add-project" method="get">
                            <button class="add-button" type="submit">Add project</button>
                        </form>
                    </c:if>           

                    <!-- Show Project List -->
                    <c:choose>
                        <c:when test="${not empty projects}">
                            <c:forEach var="project" items="${projects}">
                                <div class="project-item">
                                    <div class="project-content">
                                        <c:choose>
                                            <c:when test="${sessionScope.role == 'manager'}">
                                                <a href="task-manager?projectId=${project.projectId}" class="project-link">${project.projectName}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="task-member?projectId=${project.projectId}" class="project-link">${project.projectName}</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <p><strong>Status: </strong>${project.projectStatus}</p>
                                        <p><strong>Last Update: </strong>${project.updateAt}</p>
                                    </div>

                                    <!-- Info Button -->
                                    <form action="project-info" method="get">
                                        <input type="hidden" name="projectId" value="${project.projectId}">
                                        <button class="info-button" type="submit">Info</button>
                                    </form>
                                </div>                     
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3>${error}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:if>
    </body>
</html>
