<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/EditProfile.css">
</head>
<body>
    <div class="container">
        <h2>Edit Profile</h2>
        <c:set var="errors" value="${requestScope.errors}" />
        <form action="<%= request.getContextPath()%>/edit-profile" method="POST">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${param.email}" required>
            <c:if test="${not empty errors.email}">
                <p style="color: red; font-size: 15px">${errors.email}</p>
            </c:if>
            
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" value="${param.firstName}" required>
            <c:if test="${not empty errors.firstName}">
                <p style="color: red; font-size: 15px">${errors.firstName}</p>
            </c:if>
            
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" value="${param.lastName}" required>
            <c:if test="${not empty errors.lastName}">
                <p style="color: red; font-size: 15px">${errors.lastName}</p>
            </c:if>
            
            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phone" value="${param.phone}" required>
            <c:if test="${not empty errors.phone}">
                <p style="color: red; font-size: 15px">${errors.phone}</p>
            </c:if>
            
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" required>
            
            <div class="buttons">
                <button type="submit" class="save-btn">Save Changes</button>
                <button type="button" class="back-btn" onclick="window.location.href='<%= request.getContextPath()%>/project'">Back</button>
            </div>
        </form>
    </div>
</body>
</html>
