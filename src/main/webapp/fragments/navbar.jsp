<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 16.06.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="navbar-brand my-0 font-weight-normal">Food tracker</h5>
    <nav class="my-2 my-md-0 mr-md-3 mr-md-auto">
        <a class="p-2 text-dark" href="<c:url value = "/"/>">Main</a>
        <a class="p-2 text-dark" href="<c:url value = "/tracker"/>">Tracker</a>
        <a class="p-2 text-dark" href="<c:url value = "/food"/>">Food</a>
        <a class="p-2 text-dark" href="<c:url value = "/profile"/>">My profile</a>
    </nav>
    <c:if test="${userInformation.user.authenticated == false}">
        <a class="btn btn-outline-primary" href="<c:url value = "/login"/>">Sign in</a>
        <a class="btn btn-outline-primary" href="<c:url value = "/register"/>">Sign up</a>
    </c:if>
    <c:if test="${userInformation.user.authenticated == true}">
        <c:if test="${fn:length(userInformation.profile.firstName)>0}">
            <a class="p-2 text-dark" href="<c:url value = "/profile"/>"><c:out value="${userInformation.profile.firstName}"/></a>
        </c:if>
        <c:if test="${fn:length(userInformation.profile.firstName)==0}">
            <a class="p-2 text-dark" href="<c:url value = "/profile"/>"><c:out value="${userInformation.user.username}"/></a>
        </c:if>
        <form class="form-signin" method="POST" action="<c:url value = "/logout"/>">
            <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
            <button type="submit" class="btn btn-outline-primary">Logout</button>
        </form>
    </c:if>
</div>