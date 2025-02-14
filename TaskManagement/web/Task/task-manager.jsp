<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/task-manager.css" />
    </head>
    <body>
        <c:if test="${requestScope.error != null}">
            <p style="color: #cc0000"> ${requestScope.error} </p>
        </c:if>
        <div class="container">
            <h1>${requestScope.projectName}</h1>
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
                                <input type="hidden" name="taskId" value="${tasks.taskId}"/>
                                <input type="hidden" name="projectId" value="${param.projectId}"/>
                                <button type="submit" value="edit" name="func" class="btn-edit">Edit</button>
                            </form>
                            <form action="task-manager" method="post" class="button-form" onsubmit="return confirm('Are you sure you want to delete this task?');">
                                <input type="hidden" name="taskId" value="${tasks.taskId}"/>
                                <input type="hidden" name="projectId" value="${param.projectId}"/>
                                <button type="submit" value="delete" name="func" class="btn-delete">Delete</button>
                            </form>

                        </div>
                    </label>
                    <input class="accordion-input-checkbox" type="checkbox" id="task-${tasks.taskId}" />

                    <div class="accordion-content">
                        <p><strong>Description: </strong>${tasks.taskDescription}</p>
                        <p><strong>Status: </strong>${tasks.taskStatus}</p>
                        <p><strong>Due Date: </strong>${tasks.dueDate}</p>
                        <p><strong>Assigned Members: </strong>${tasks.assignedTo}</p>
                        <p><strong>Create At: </strong> ${tasks.createAt}</p>
                        <p><strong>Update At: </strong> ${tasks.updateAt}</p>
                        <p><strong>Link Submission: </strong> ${tasks.linkSubmission}</p>
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
    </body>

</html>