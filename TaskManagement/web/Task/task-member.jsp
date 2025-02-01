<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Tasks</title>
    <link rel="stylesheet" href="../CSS/task.css"/>
</head>
<body>

<c:if test="${requestScope.error != null}">
    <p style="color: #cc0000"> ${requestScope.error} </p>
</c:if>

<form method="post" action="">
    <div class="project-container">
        <h1>${requestScope.projectName}</h1> <!-- Project Name -->

        <!-- Lặp qua danh sách tasks -->
        <c:forEach items="${requestScope.tasks}" var="tasks" varStatus="status">
            <div class="accordion">
                <!-- Task Header: Bấm vào để mở/đóng -->
                <label for="task${status.index}" class="accordion-header">
                    ${status.index + 1}. ${tasks.taskName} - ${tasks.taskStatus}
                </label>
                <input type="checkbox" id="task${status.index}" class="accordion-toggle" />

                <!-- Task Details (ẩn/hiện khi click) -->
                <div class="accordion-content">
                    <p><strong>Description:</strong> ${tasks.taskDescription}</p>
                    <p><strong>Due Date:</strong> ${tasks.dueDate}</p>
                    <p><strong>Assigned Members:</strong> ${tasks.assignedTo}</p>
                    <p><strong>Create At:</strong> ${tasks.createAt}</p>
                    <p><strong>Update At:</strong> ${tasks.updateAt}</p>

                    <!-- Nút View Submission -->
                    <c:choose>
                        <c:when test="${tasks.linkSubmission != null && tasks.linkSubmission != ''}">
                            <button onclick="window.open('${tasks.linkSubmission}', '_blank')">View Submission</button>
                        </c:when>
                        <c:otherwise>
                            <button disabled style="cursor: not-allowed; background-color: #999;">No Submission</button>
                        </c:otherwise>
                    </c:choose>

                    <!-- Form để Update Link Submission -->
                    <form method="post" action="">
                        <input type="hidden" name="taskId" value="${tasks.taskId}">
                        <label for="linkSubmission${status.index}">Link Submission:</label>
                        <input type="url" id="linkSubmission${status.index}" name="link" value="${tasks.linkSubmission}" placeholder="Enter link here">
                        <button type="submit">Update Task</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</form>

</body>
</html>
