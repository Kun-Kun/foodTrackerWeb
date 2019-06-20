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
    <div class="d-flex justify-content-around align-items-center border border-primary rounded">
        <div class="p-2">Fats: <c:out value="${food.fats}"/> g.</div>
        <div class="p-2">Proteins: <c:out value="${food.proteins}"/> g.</div>
        <div class="p-2">Carbohydrates: <c:out value="${food.carbohydrates}"/> g.</div>
        <div class="p-2">Kilocalories: <c:out value="${food.kilocalories}"/> kcal.</div>
        <div class="p-2">Weight: <c:out value="${food.portionWeight}"/> g.</div>
        <c:if test="${userInformation.user.authenticated == true}">
            <div class="p-2"><a href="#" class="add-to-tracker btn btn-light" data-id="<c:out value="${food.id}"/>">+</a></div>

            <c:if test="${userInformation.profile.userId == food.creatorId}">
                <div class="p-2"><a class="btn btn-light" href="<c:url value = "/food/edit?food=${food.id}"/>">Edit</a></div>
                <div class="p-2">
                    <form method="POST" action="<c:url value = "/food/delete"/>">
                        <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
                        <input type="hidden" name="id" value="<c:out value="${food.id}" default="${id}"/>"/>
                        <button type="submit" class="delete-food btn btn-light" data-id="<c:out value="${food.id}"/>">Delete</button>
                    </form>
                </div>
            </c:if>

        </c:if>
    </div>
    <hr>
    <h2 class="mb-0"><c:out value="${food.title}"/></h2>
    <p><c:out value="${food.description}"/></p>

    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>