
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
</head>
<body>

<form action="LoginServlet" method="post">

    Username: <input type="text" name="user"/>
    <br/>
    Password: <input type="password" name="pwd"/>
    <br/>
    <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>