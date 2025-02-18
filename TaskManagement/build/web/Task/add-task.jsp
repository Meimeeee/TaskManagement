<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <title>Add New Task</title>
        <link rel="stylesheet" href="CSS/add-task.css" />
    </head>

    <body>
        <form action="project" method="get">
            <button class="go-back-button" type="submit">Go back</button>
        </form>
        <div class="form-container">
            <div class="title-comtainer">
                <h1 class="form-title">Add New Task</h1>
            </div>
            
            <div class="error-container">
                <c:if test="${requestScope.error != null}">
                    <p class="error-message">${requestScope.error}</p>
                </c:if>
            </div>
            <form action="" method="post" class="task-form">
                <input type="hidden" name="projectId" value="${param.projectId}">
                <div class="form-group">
                    <label for="taskName">Task Name</label>
                    <input id="taskName" name="taskName" type="text" placeholder="Enter task name" required />
                </div>
                <div class="form-group">
                    <label for="taskDescription">Task Description</label>
                    <textarea id="taskDescription" name="taskDescription" placeholder="Describe the task"
                        required></textarea>
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
                    <input id="dueDate" name="dueDate" type="date" required />
                    <script>
                        document.addEventListener("DOMContentLoaded", function () {
                            let dobInput = document.getElementById("dob");
                            let dobError = document.getElementById("dobError");

                            let minDate = new Date("1990-01-01");
                            let maxDate = new Date();

                            dobInput.setAttribute("min", minDate.toISOString().split("T")[0]);
                            dobInput.setAttribute("max", maxDate.toISOString().split("T")[0]);

                            dobInput.addEventListener("change", function () {
                                let dobValue = new Date(this.value);

                                if (dobValue < minDate || dobValue > maxDate || isNaN(dobValue.getTime())) {
                                    dobError.textContent = "Invalid date.";
                                    this.value = "";
                                } else {
                                    dobError.textContent = "";
                                }
                            });
                        });
                    </script>
                </div>
                <button name="func" value="add" class="btn-submit" type="submit">Add Task</button>
            </form>
        </div>
    </body>

    </html>