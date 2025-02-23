<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/task-member.css" />
    </head>

    <body>

        <form action="project" method="get">
            <button class="go-back-button" type="submit">Go back</button>
        </form>
        <!--<form method="post" action="">-->
        <div class="container">
            <h1>${requestScope.projectName}</h1>
            <c:if test="${requestScope.error != null}">
                <p style="color: #cc0000"> ${requestScope.error} </p>
            </c:if>

            <form method="get">
                <div class="sort-buttons">
                    <input type="hidden" name="projectId" value="${param.projectId}"/>
                    <select id="sortInput" name="sort">
                        <option value="date">Sort By Date</option>
                        <option value="status">Sort By Status</option>
                    </select>
                    <button type="submit">Sort</button>
                </div>
            </form>

            <form method="get">
                <div class="search">
                    <input type="hidden" name="projectId" value="${param.projectId}"/>
                    <input id="searchInput" type="text" name="search" placeholder="Search"/>
                    <button type="submit">Search</button>
                </div>
            </form>

            <c:forEach items="${requestScope.tasks}" var="tasks" varStatus="status">
                <div class="accordion">
                    <label class="accordion-label" for="task-${tasks.taskId}">
                        <div class="task-header">
                            <span class="task-name">${tasks.taskName}</span>
                        </div>
                    </label>
                    <input class="accordion-input-checkbox" type="checkbox" id="task-${tasks.taskId}" />

                    <div class="accordion-content">
                        <p><strong>Description: </strong>${tasks.taskDescription}</p>
                        <p><strong>Status: </strong>${tasks.taskStatus}</p>
                        <p><strong>Due Date: </strong><fmt:formatDate value="${tasks.dueDate}" pattern="yyyy-MM-dd"/></p>
                        <p><strong>Assigned Members: </strong>${tasks.assignedTo}</p>
                        <p><strong>Create At: </strong><fmt:formatDate value="${tasks.createAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        <p><strong>Update At: </strong><fmt:formatDate value="${tasks.updateAt}" pattern="yyyy-MM-dd HH:mm:ss"/></p>


                        <form method="post" action="">
                            <div class="link-submission">
                                <input type="hidden" name="taskId" value="${tasks.taskId}">
                                <label for="linkSubmission${tasks.taskId}">
                                    <p><strong>Link Submission</strong></p>
                                </label>
                                <input type="url" id="linkSubmission${tasks.taskId}" name="link"
                                       value="${tasks.linkSubmission}" placeholder="Enter link here">
                                <button type="submit">Update</button>
                            </div>
                        </form>

                        <div class="task-actions">
                            <c:choose>
                                <c:when test="${tasks.linkSubmission != null && tasks.linkSubmission != ''}">
                                    <button class="button-submit"
                                            onclick="window.open('${tasks.linkSubmission}', '_blank')">View
                                        Submission</button>
                                    </c:when>
                                    <c:otherwise>
                                    <button disabled style="cursor: not-allowed; background-color: #999999;">No
                                        Submission</button>
                                    </c:otherwise>
                                </c:choose>
                        </div>

                    </div>
                </div>
            </c:forEach>
        </div>
        <!--</form>-->
    </body>

</html>