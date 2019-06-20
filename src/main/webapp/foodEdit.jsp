<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="${food.title}"  scope="request"/></c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp" />
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp" />

    <form action="<c:url value = "/food/edit"/>" method="POST">
        <c:if test="${(empty id) &&(not empty food.id)}"><c:set var="id" value="${food.id}"  scope="request"/></c:if>
        <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
        <input type="hidden" name="id" value="<c:out value="${food.id}" default="${id}"/>"/>
        <div class="form-group">
            <label for="inputTitle">Title</label>
            <input type="text" class="form-control" id="inputTitle" placeholder="Burger" name="title" value="<c:out value="${food.title}" default="${title}"/>">
        </div>
        <div class="form-group">
            <label for="inputDescription">Description</label>
            <textarea class="form-control" id="inputDescription" name="description" rows="8"><c:out value="${food.description}" default="${description}"/></textarea>
        </div>
        <p>To facilitate comparison, specific values are quoted as "value per 100 g"</p>
        <div class="row">
            <div class="col">
                <label for="inputFats">Fats: </label>
                <input type="text" class="form-control" id="inputFats" placeholder="g." name="fats" value="<c:out value="${food.fats}" default="${fats}"/>">
            </div>
            <div class="col">
                <label for="inputProteins">Proteins: </label>
                <input type="text" class="form-control" id="inputProteins" placeholder="g." name="proteins" value="<c:out value="${food.proteins}" default="${proteins}"/>">
            </div>
            <div class="col">
                <label for="inputCarbohydrates">Carbohydrates: </label>
                <input type="text" class="form-control" id="inputCarbohydrates" placeholder="g." name="carbohydrates" value="<c:out value="${food.carbohydrates}" default="${carbohydrates}"/>">
            </div>
            <div class="col">
                <label for="inputKilocalories">Kilocalories: </label>
                <input type="text" class="form-control" id="inputKilocalories" placeholder="kcal" name="kilocalories" value="<c:out value="${food.kilocalories}" default="${kilocalories}"/>">
            </div>
            <div class="col">
                <label for="inputWeight">Weight: </label>
                <input type="text" class="form-control" id="inputWeight" placeholder="g." name="weight" value="<c:out value="${food.portionWeight}" default="${weight}"/>">
            </div>
        </div>
        <hr>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center" role="alert">
                <c:out value="${error}" />
            </div>
        </c:if>
        <button type="submit" class="btn btn-success">Save</button>
        <c:if test="${empty id}">
            <a href="<c:url value = "/food"/>" class="btn btn-danger">Cancel</a>
        </c:if>
        <c:if test="${not empty id}">
            <a href="<c:url value = "/food/info?food=${id}"/>" class="btn btn-danger">Cancel</a>
        </c:if>
    </form>

    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>