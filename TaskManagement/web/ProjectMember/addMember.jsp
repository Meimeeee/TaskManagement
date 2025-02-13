<%-- 
    Document   : addMember
    Created on : Feb 12, 2025, 9:31:32 PM
    Author     : Huynh Han Dong
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Member</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty sessionScope.id}">
                <p>Coming Soon!</p>
            </c:when>
        </c:choose>    
    </body>
</html>
