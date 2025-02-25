<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="CSS/task-manager.css" />
</head>

<body>
    <form action="project" method="get">
        <button class="go-back-button" type="submit">Go back</button>
    </form>

    <div class="container">
        <h1>${requestScope.projectName}</h1>
        <c:if test="${requestScope.error != null}">
            <p style="color: #cc0000"> ${requestScope.error} </p>
        </c:if>

        <form method="get" class="sort-container">
            <input type="hidden" name="projectId" value="${param.projectId}" />
            <select id="sortInput" name="sort">
                <option value="date">Sort By Date</option>
                <option value="status">Sort By Status</option>
            </select>
            <button type="submit">Sort</button>
        </form>

        <form method="get" class="search-container">
            <input type="hidden" name="projectId" value="${param.projectId}" />
            <input id="searchInput" type="text" name="search" placeholder="Search" />
            <button type="submit">Search</button>
        </form>

        <div class="add-task-button">
            <form action="task-manager" method="get">
                <input type="hidden" name="projectId" value="${param.projectId}">
                <button name="func" value="add" type="submit" class="add-button">+</button>
            </form>
        </div>
        <c:forEach items="${requestScope.tasks}" var="tasks" varStatus="status">
            <div class="accordion">

                <label class="accordion-label" for="task-${tasks.taskId}">
                    <div class="task-header">
                        <span class="task-name">${tasks.taskName}</span>
                    </div>
                    <div class="button-header">

                        <form action="task-manager" method="get" class="button-form">
                            <input type="hidden" name="taskId" value="${tasks.taskId}" />
                            <input type="hidden" name="projectId" value="${param.projectId}" />
                            <button type="submit" value="edit" name="func" class="btn-edit">Edit</button>
                        </form>
                        <form action="task-manager" method="post" class="button-form"
                            onsubmit="return confirm('Are you sure you want to delete this task?');">
                            <input type="hidden" name="taskId" value="${tasks.taskId}" />
                            <input type="hidden" name="projectId" value="${param.projectId}" />
                            <button type="submit" value="delete" name="func" class="btn-delete">Delete</button>
                        </form>

                    </div>
                </label>
                <input class="accordion-input-checkbox" type="checkbox" id="task-${tasks.taskId}" />

                <div class="accordion-content">
                    <p><strong>Description: </strong>${tasks.taskDescription}</p>
                    <p><strong>Status: </strong>${tasks.taskStatus}</p>
                    <p><strong>Due Date: </strong>
                        <fmt:formatDate value="${tasks.dueDate}" pattern="yyyy-MM-dd" />
                    </p>
                    <p><strong>Assigned Members: </strong>${tasks.assignedTo}</p>
                    <p><strong>Create At: </strong>
                        <fmt:formatDate value="${tasks.createAt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </p>
                    <p><strong>Update At: </strong>
                        <fmt:formatDate value="${tasks.updateAt}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </p>
                    <p><strong>Link Submission: </strong> ${tasks.linkSubmission}</p>
                    <div class="task-actions">
                        <c:choose>
                            <c:when test="${tasks.linkSubmission != null && tasks.linkSubmission != ''}">
                                <button class="button-submit"
                                    onclick="window.open('${tasks.linkSubmission}', '_blank')">View
                                    Submission</button>
                            </c:when>
                            <c:otherwise>
                                <button disabled style="cursor: not-allowed; background-color: #b4b4b4;">No
                                    Submission</button>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
</body>

</html>