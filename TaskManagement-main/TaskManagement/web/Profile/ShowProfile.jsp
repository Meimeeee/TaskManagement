<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Profile</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/ShowProfile.css">

    </head>
    <body>
        <div class="container">
            <h2>Your Profile</h2>
            <div class="profile-info">
                <p><span class="label">Email:</span> ${requestScope.email}</p>
                <p><span class="label">First name:</span> ${requestScope.firstName}</p>
                <p><span class="label">Last name:</span> ${requestScope.lastName}</p>
                <p><span class="label">Phone number:</span> ${requestScope.phoneNumber}</p>
                <p><span class="label">Date of birth:</span> ${requestScope.dob}</p>
                <button class="btn-back" onclick="window.location.href = '${pageContext.request.contextPath}/project'">Back to Home</button>

            </div>
        </div>
    </body>
</html>
