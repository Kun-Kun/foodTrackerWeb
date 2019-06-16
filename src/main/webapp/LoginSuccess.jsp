
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<%--<%--%>
    <%--//allow access only if session exists--%>
    <%--String user = (String) session.getAttribute("user");--%>
    <%--String userName = null;--%>
    <%--String sessionID = null;--%>
    <%--Cookie[] cookies = request.getCookies();--%>
    <%--if(cookies !=null){--%>
        <%--for(Cookie cookie : cookies){--%>
            <%--if(cookie.getName().equals("user")) userName = cookie.getValue();--%>
            <%--if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();--%>
        <%--}--%>
    <%--}--%>
<%--%>--%>
<c:out value="${userInformation.user.username}" default="guest" />
<br>
<c:out value="${userInformation.profile.firstName}" default="guest" />
<h3>Hi <%--<%=userName %>--%>, Login successful. Your Session ID=<%--<%=sessionID %>--%></h3>
<br>
User=<%--<%=user %>--%>
<br>
<a href="CheckoutPage.jsp">Checkout Page</a>
<form action="LogoutServlet" method="post">
    <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
    <input type="submit" value="Logout" >
</form>
</body>
</html>