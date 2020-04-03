<%-- 
    Document   : index
    Created on : Apr 3, 2020, 12:40:53 PM
    Author     : Xavier Vani-Charron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action = "LoginServlet" method = "POST">
            <h1>Username</h1>
            <input type="text" name="username" />
            <h1>Password</h1>
            <input type="text" name="password" />
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
