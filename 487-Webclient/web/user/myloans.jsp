<%-- 
    Document   : myloans
    Created on : Apr 5, 2020, 3:53:10 PM
    Author     : xavie
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pkg487.loan.core.Loan"%>
<%@page import="pkg487.webclient.SOAPClient"%>
<%@page import="pkg487.library.core.Book"%>
<%@page import="pkg487.webclient.RESTClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <a class="navbar-item" href="<%= request.getContextPath() %>/user/library.jsp">My Loans</a>
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
    <section class="hero">
        <div clas="hero-body">
            <div class="container">
                <h1 class="title">My Loans</h1>
            </div>
        </div>
    </section>
    <section class="section">
        <div class="container">
            <table class="table">
                    <thead>
                        <tr>
                            <th>Loan Id</th>
                            <th>Book Id</th>
                            <th>Date Borrowed</th>
                            <th>Date Returned </th>
                            <th>Return Book</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            Integer userId = (Integer) session.getAttribute("userId");
                            SOAPClient client = new SOAPClient();
                            Loan[] loans = client.listLoansByMemberId(userId);
                            pageContext.setAttribute("loans", loans);
                        %>
                        <c:forEach items="${loans}" var="loan">
                            <tr>
                                <form action="<%= request.getContextPath()%>/ReturnBook" method="POST">
                                    <td><input class="input" type="text" name="id" value="${loan.getId()}" readonly/></td>
                                    <td>${loan.getBookId()}</td>
                                    <td>${loan.getDateBorrowed()}</td>
                                    <td>${loan.getDateReturned()} </td>
                                    <td><input class="button" type="submit" name="return" value="Return"/></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table>
        </div>
    </section>
    <script async type="text/javascript" src="<%= request.getContextPath() %>/js/bulma.js"></script>
</body>

</html>