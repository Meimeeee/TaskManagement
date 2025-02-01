
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/task.css"/>
    </head>
    <body>
        <c:if test="${requestScope.error != null}">
            <p style="color: #cc0000"> ${requestScope.error} </p>
        </c:if>
        <form method="post" action="">
            <div class="container">
                <h1>${requestScope.projectName}</h1>
                <c:forEach items="${requestScope.tasks}" var="tasks" varStatus="status">
                    <div class="accordion">
                        <label class="accordion-label" for="task-${tasks.taskId}">                                
                            <div class="task-header">
                                <span class="task-id">${status.index+1}</span>
                                <span class="task-name">${tasks.taskName}</span>
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
                            <div class="task-actions">
                                <c:choose>
                                    <c:when test="${tasks.linkSubmission != null && tasks.linkSubmission != ''}">
                                        <button onclick="window.open('${tasks.linkSubmission}', '_blank')">View Submission</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button disabled style="cursor: not-allowed; background-color: #999999;">No Submission</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="link-submission">
                                <form method="post" action="">
                                    <input type="hidden" name="taskId" value="${tasks.taskId}">
                                    <label for="linkSubmission">Link Submission</label>
                                    <input type="url" id="linkSubmission" name="link" value="${tasks.linkSubmission}" placeholder="Enter link here">
                                    <button type="submit">Update</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form>
    </body>
</html>
