<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="Food page"  scope="request"/></c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp"/>
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp"/>
    <div class="row pb-3">
        <div class="col-md-10">
            <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" id='#foodSearch-typeahead'>
        </div>
        <div class="col">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Add custom</button>
        </div>
    </div>

    <div id="typeahead-target">
        <c:import url="fragments/parts/foodCardList.jsp"/>
    </div>
    <c:import url="fragments/footer.jsp"/>
</div>


</body>
</html>