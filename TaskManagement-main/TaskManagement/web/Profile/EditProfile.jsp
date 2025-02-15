<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/EditProfile.css">
    </head>
    <body>
        <div class="container">
            <c:set var="errors" value="${requestScope.errors}" />
            <h2>Edit Profile</h2>
            <form action="<%= request.getContextPath()%>/update-profile" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${requestScope.email}" required>
                    <c:if test="${not empty errors.email}">
                        <p style="color: red; font-size: 15px">${errors.email}</p>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${requestScope.firstName}" required>
                    <c:if test="${not empty errors.firstName}">
                        <p style="color: red; font-size: 15px">${errors.firstName}</p>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${requestScope.lastName}" required>
                    <c:if test="${not empty errors.lastName}">
                        <p style="color: red; font-size: 15px">${errors.lastName}</p>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phone" name="phoneNumber" value="${requestScope.phoneNumber}" required>
                    <c:if test="${not empty errors.phoneNumber}">
                        <p style="color: red; font-size: 15px">${errors.phoneNumber}</p>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="dob" class="form-label">Date of Birth</label>
                    <input type="date" class="form-control" id="dob" name="dob"  required>
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
                                    dobError.textContent = "Ngày sinh phải từ 01/01/1990 đến hôm nay.";
                                    this.value = "";
                                } else {
                                    dobError.textContent = "";
                                }
                            });
                        });
                    </script>
                </div>
                <button type="submit" class="btn btn-primary">Update Profile</button>
                <button class="btn-back" onclick="window.location.href = '${pageContext.request.contextPath}/project'">Back to Home</button>
            </form>
        </div>
    </body>
</html>
