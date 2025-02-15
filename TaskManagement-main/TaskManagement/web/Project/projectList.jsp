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
        <link rel="stylesheet" type="text/css" href="CSS/Project.css"/>
        <title>Home Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty sessionScope.id}">
                <div class="container">
                    <div class="sidebar">
                        <p>Profile</p>
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/Account/EditAccount.jsp">Edit Account</a></li>
                            <li><a href="${pageContext.request.contextPath}/Profile/EditProfile.jsp">Edit Profile</a></li>
                            <li><a href="${pageContext.request.contextPath}/show-profile">Show Profile</a></li>
                                <c:if test="${sessionScope.role eq 'admin'}">
                                <li><a href="${pageContext.request.contextPath}/Account/CreateManager.jsp">Create Manager Account</a></li>
                                </c:if>
                        </ul>
                    </div>

                    <div class="main-content">
                        <h2>Welcome, <c:out value="${sessionScope.username}"/>!</h2>

                        <!-- Add Project Button -->
                        <c:choose>
                            <c:when test="${sessionScope.role == 'manager'}">
                                <form action="add-project" method="get">
                                    <button class="add-project-button" type="submit">Add project</button>
                                </form>
                            </c:when>
                        </c:choose>
                        <!-- Show Project List -->
                        <c:choose>
                            <c:when test="${not empty projects}">
                                <c:forEach var="project" items="${projects}">
                                    <div class="project-item">
                                        <div class="project-content">

                                            <!-- Link To Task -->
                                            <a href="task?projectId=${project.projectId}" class="project-link">${project.projectName}</a>
                                            <p><strong>Status: </strong>${project.projectStatus}</p>
                                            <p><strong>Last Update: </strong>${project.updateAt}</p>
                                        </div>
                                        <div class="info-button">
                                            <form action="project-info" method="get">
                                                <input type="hidden" name="projectId" value="${project.projectId}">
                                                <button type="submit">Info</button>
                                            </form>
                                        </div>
                                    </div>                     
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h3>${error}</h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <h3>${error}</h3>
            </c:otherwise>
        </c:choose>
    </body>
</html>
