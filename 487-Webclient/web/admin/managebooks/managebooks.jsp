<%-- 
    Document   : managebooks
    Created on : Apr 4, 2020, 3:04:08 PM
    Author     : Xavier Vani-Charron
--%>

<%@page import="java.util.List"%>
<%@page import="pkg487.library.core.Book"%>
<%@page import="pkg487.webclient.RESTClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//Check if User is Admin
if(session == null){
    response.sendRedirect(request.getContextPath());
}else if(null == session.getAttribute("authLevel")){
    response.sendRedirect(request.getContextPath());
}else if(!session.getAttribute("authLevel").equals(0)){
    response.sendRedirect(request.getContextPath());
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manage Users</title>
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
                <a class="navbar-item brand-text" href="<%= request.getContextPath() %>/admin/home.jsp">Library Management System</a>
                <div class="navbar-burger burger" data-target="navMenu">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div id="navMenu" class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item" href="<%= request.getContextPath() %>/admin/home.jsp">Home</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/admin/managebooks/managebooks.jsp">Manage Books</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/admin/manageloans/manageloans.jsp">Manage Loans</a>
                    <a class="navbar-item" href="<%= request.getContextPath() %>/admin/manageusers/manageusers.jsp">Manage Users</a>
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
        <section class="hero">
            <div clas="hero-body">
                <div class="container">
                    <h1 class="title">Book Management</h1>
                    <h2 class="subtitle">Edit Book Info</h2>
                </div>
            </div>
        </section>
        
        <section class="section">
            <div class="container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Book Id</th>
                            <th>Book Title</th>
                            <th>Book Description</th>
                            <th>Book ISBN</th>
                            <th>Book Author</th>
                            <th>Book Publisher</th>
                            <th>Edit Book</th>
                            <th>Delete Book</th>
                        </tr>
                    </thead>
                    <tbody>                           
                        <%
                            RESTClient client = new RESTClient();
                            List<Book> books = client.listBooks("json");
                            pageContext.setAttribute("books", books);
                        %>
                        
                        <c:forEach items="${books}" var="book">
                            <tr>
                                <form action="<%= request.getContextPath()%>/ManageBooks" method="POST">
                                    <td><input class="input" type="text" name="id" value="${book.getId()}" readonly/></td>
                                    <td><input class="input" type="text" name="title" value="${book.getTitle()}"/></td>
                                    <td><input class="input" type="text" name="desc" value="${book.getBookDesc()}"/></td>
                                    <td><input class="input" type="text" name="isbn" value="${book.getIsbn()}"/></td>
                                    <td><input class="input" type="text" name="author" value="${book.getAuthor()}"/></td>
                                    <td><input class="input" type="text" name="pub" value="${book.getPublisher()}"/></td>
                                    <td><input class="button" type="submit" name="save" value="Save"/></td>
                                    <td><input class="button" type="submit" name="delete" value="Delete"/></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="section">
            <form action="<%= request.getContextPath()%>/ManageBooks" method="POST">
            <div class="container">
                <div class="field">
                    <label class="label">Book Title</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="title" name="title" required/>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Book Description</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="desc" name="desc" required/>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Book ISBN</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="isbn" name="isbn" required/>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Book Author</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="author" name="author" required/>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Book Publisher</label>
                    <div class="control">
                        <input class="input" type="text" placeholder="pub" name="pub" required/>
                    </div>
                </div>
                <div class="field">
                    <div class="control">
                        <input class="button" type="submit" name="create" value="Create" required/>
                    </div>
                </div>
            </div>
            </form>
        </section>
   </body>
    <script async type="text/javascript" src="<%= request.getContextPath() %>/js/bulma.js"></script>
</html>
