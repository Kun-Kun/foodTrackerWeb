<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 19.06.2019
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
    <div class="col-auto d-none d-lg-block">
        <img src="<c:url value = "/static/img/1-200.jpg"/>" class="bd-placeholder-img" alt="sans">
    </div>
    <div class="col p-4 d-flex flex-column position-static">

        <h3 class="mb-0"><c:out value="${foodCard.title}"/></h3>

        <p class="card-text mb-auto"><c:out value="${foodCard.description}"/></p>
        <div class="d-flex align-items-center">
            <div class="p-2">Fats: <c:out value="${foodCard.fats}"/></div>
            <div class="p-2">Proteins: <c:out value="${foodCard.proteins}"/></div>
            <div class="p-2">Carbohydrates: <c:out value="${foodCard.carbohydrates}"/></div>
            <div class="p-2">Kilocalories: <c:out value="${foodCard.kilocalories}"/></div>
            <div class="p-2 flex-grow-1 "></div>
            <div class="p-2 bd-highlight"><a href="<c:url value = "/food/info?food=${foodCard.id}"/>" >More...</a></div>
            <div class="p-2 bd-highlight"><a href="#" class="add-to-tracker" data-id="<c:out value="${foodCard.id}"/>">+</a></div>
        </div>
    </div>

</div>