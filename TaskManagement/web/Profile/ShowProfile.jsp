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
            <label>Email:</label>
            <span>${requestScope.email}</span>
        </div>
        <div class="profile-info">
            <label>First Name:</label>
            <span>${requestScope.firstName}</span>
        </div>
        <div class="profile-info">
            <label>Last Name:</label>
            <span>${requestScope.lastName}</span>
        </div>
        <div class="profile-info">
            <label>Phone Number:</label>
            <span>${requestScope.phoneNumber}</span>
        </div>
        <div class="profile-info">
            <label>Date of Birth:</label>
            <span>${requestScope.dob}</span>
        </div>
        <div class="buttons">
            <button type="button" class="back-btn" onclick="window.location.href='<%= request.getContextPath()%>/project'">Back</button>
        </div>
    </div>
</body>
</html>
