<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="${food.title}"  scope="request"/></c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp" />
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp" />
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner" style=" width:100%; height: 500px !important;">
            <div class="carousel-item active">
                <img src="<c:url value = "/static/img/1.jpg"/>" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="<c:url value = "/static/img/2.jpg"/>" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="<c:url value = "/static/img/3.jpg"/>" class="d-block w-100" alt="...">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <hr>
    <div class="d-flex justify-content-around border border-primary rounded">
        <div class="p-2">Fats: <c:out value="${food.fats}"/> g.</div>
        <div class="p-2">Proteins: <c:out value="${food.proteins}"/> g.</div>
        <div class="p-2">Carbohydrates: <c:out value="${food.carbohydrates}"/> g.</div>
        <div class="p-2">Kilocalories: <c:out value="${food.kilocalories}"/> kcal.</div>
        <div class="p-2">Weight: <c:out value="${food.portionWeight}"/> g.</div>
        <div class="p-2"><a href="#" class="add-to-tracker" data-id="<c:out value="${food.id}"/>">+</a></div>
    </div>
    <hr>
    <h2 class="mb-0"><c:out value="${food.title}"/></h2>
    <p><c:out value="${food.description}"/></p>

    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>