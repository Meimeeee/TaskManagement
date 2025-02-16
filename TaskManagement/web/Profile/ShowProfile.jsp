<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Show Profile</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/ShowProfile.css">
    </head>
    <body>
        <div class="container">
            <h2>Profile Information</h2>
            <div class="profile-info">
                <label>Email:</label>  ${requestScope.email} 
            </div>
            <div class="profile-info">
                <label>First Name:</label> ${requestScope.firstName}
            </div>
            <div class="profile-info">
                <label>Last Name:</label> ${requestScope.lastName}
            </div>
            <div class="profile-info">
                <label>Phone Number:</label> ${requestScope.phoneNumber}
            </div>
            <div class="profile-info">
                <label>Date of Birth:</label> ${requestScope.dob}
            </div>
            <div class="buttons">
                <button type="button" class="back-btn" onclick="window.location.href = '<%= request.getContextPath()%>/project'">Back</button>
            </div>
        </div>
    </body>
</html>
