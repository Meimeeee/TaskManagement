<%@page import="java.util.List"%>
<%@page import="DTO.AccountDTO"%>
<%@page import="DTO.TaskDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/edit-task.css">
    </head>

    <body>
        <%
            TaskDTO t = (TaskDTO) request.getAttribute("t");
        %>
        <div class="form-container">
            <h1 class="form-title">Edit Task</h1>
            <div class="error-container">
                <c:if test="${requestScope.error != null}">
                    <p class="error-message">${requestScope.error}</p>
                </c:if>
            </div>
            <form action="" method="post" class="task-form">
                <input type="hidden" name="projectId" value="${param.projectId}">
                <input type="hidden" name="taskId" value="<%= t.getTaskId()%>" />
                <div class="form-group">
                    <label for="taskName">Task Name</label>
                    <input id="taskName" name="taskName" type="text" value="<%= t.getTaskName()%>"
                           placeholder="Enter task name" required />
                </div>

                <div class="form-group">
                    <label for="taskDescription">Task Description</label>
                    <textarea id="taskDescription" name="taskDescription" placeholder="Describe the task"
                              required><%= t.getTaskDescription()%></textarea>
                </div>

                <div class="form-group">
                    <label for="assignedTo">Assigned To</label>
                    <select id="assignedTo" name="assignedTo">
                        <c:forEach items="${requestScope.accounts}" var="account" varStatus="status">
                            <option value="${account.accountId}">${account.username}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="dueDate">Due Date</label>
                    <input id="dueDate" name="dueDate" type="date" value="<%= t.getDueDate()%>" required />
                </div>

                <button name="func" value="edit" class="btn-submit" type="submit">Comfirm</button>

            </form>
        </div>
    </body>

</html>