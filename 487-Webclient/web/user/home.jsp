<%-- 
    Document   : home
    Created on : Apr 3, 2020, 1:42:49 PM
    Author     : Xavier Vani-Charron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Homepage</title>
    </head>
    <body>
        <% 
            if((int) session.getAttribute("authLevel") != 0){
                response.sendRedirect("login.jsp");
            }
        %>
    </body>
</html>
