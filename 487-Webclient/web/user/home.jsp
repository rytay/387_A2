<%-- 
    Document   : home
    Created on : Apr 3, 2020, 1:42:49 PM
    Author     : Xavier Vani-Charron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//Check if User is Admin
if(session == null){
    response.sendRedirect(request.getContextPath());
}else if(null == session.getAttribute("authLevel")){
    response.sendRedirect(request.getContextPath());
}else if(!session.getAttribute("authLevel").equals(1)){
    response.sendRedirect(request.getContextPath());
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library Management System</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <!-- Bulma Version 0.8.x-->
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.8.0/css/bulma.min.css" />
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/admin.css">
</head>

<body>
    
    <!-- START NAV -->
    <nav class="navbar is-white">
        <div class="container">
            <div class="navbar-brand">
                <a class="navbar-item brand-text" href="../index.html">
          User Library System
        </a>
                <div class="navbar-burger burger" data-target="navMenu">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div id="navMenu" class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item" href="<%= request.getContextPath() %>/user/home.jsp">Home</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/user/library.jsp">Library</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/user/myloans.jsp">My Loans</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/user/account.jsp">My Account</a>
                </div>
                <div class="navbar-end">
                    <div class="navbar-item">
                        <form action="<%= request.getContextPath() %>/LogOutServlet" method="POST">
                            <input class="button" type="submit" name="logout" value="logout" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <!-- END NAV -->
    <div class="container">
        <h1>Welcome <% out.println(session.getAttribute("username")); %> </h1>
    </div>                  
    <script async type="text/javascript" src="<%= request.getContextPath() %>/js/bulma.js"></script>
</body>

</html>
