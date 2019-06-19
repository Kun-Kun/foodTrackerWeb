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
        <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
        <input type="hidden" name="id" value="${food.id}"/>
        <div class="form-group">
            <label for="inputTitle">Title</label>
            <input type="text" class="form-control" id="inputTitle" placeholder="Burger" value="<c:out value="${food.title}"/>">
        </div>
        <div class="form-group">
            <label for="inputDescription">Description</label>
            <textarea class="form-control" id="inputDescription" rows="8"><c:out value="${food.description}"/></textarea>
        </div>
        <p>To facilitate comparison, specific values are quoted as "value per 100 g"</p>
        <div class="row">
            <div class="col">
                <label for="inputFats">Fats: </label>
                <input type="text" class="form-control" id="inputFats" placeholder="g." value="<c:out value="${food.fats}"/>">
            </div>
            <div class="col">
                <label for="inputProteins">Proteins: </label>
                <input type="text" class="form-control" id="inputProteins" placeholder="g." value="<c:out value="${food.proteins}"/>">
            </div>
            <div class="col">
                <label for="inputCarbohydrates">Carbohydrates: </label>
                <input type="text" class="form-control" id="inputCarbohydrates" placeholder="g." value="<c:out value="${food.carbohydrates}"/>">
            </div>
            <div class="col">
                <label for="inputKilocalories">Kilocalories: </label>
                <input type="text" class="form-control" id="inputKilocalories" placeholder="kcal" value="<c:out value="${food.kilocalories}"/>">
            </div>
            <div class="col">
                <label for="inputWeight">Weight: </label>
                <input type="text" class="form-control" id="inputWeight" placeholder="g." value="<c:out value="${food.portionWeight}"/>">
            </div>
        </div>
        <hr>
        <button type="submit" class="btn btn-success">Save</button><button type="button" class="btn btn-danger">Cancel</button>
    </form>

    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>