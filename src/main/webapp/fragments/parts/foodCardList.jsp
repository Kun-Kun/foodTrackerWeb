<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 19.06.2019
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="foodCard" items="${foodCards}">
    <c:set var="foodCard" value="${foodCard}" scope="request"/>
    <c:import url="/fragments/parts/foodCard.jsp"/>
</c:forEach>