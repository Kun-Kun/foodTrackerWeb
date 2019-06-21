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
            <form action="<c:url value = "/food"/>" method="GET">
                <div class="row pb-3">

                    <div class="col-md-9">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="query" id='foodSearch-typeahead'>
                    </div>
                    <div class="col">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" >Search</button>
                    </div>
                    <c:if test="${userInformation.user.authenticated == true}">
                        <div class="col">
                            <a href="<c:url value = "/food/edit"/>" class="btn btn-outline-success my-2 my-sm-0">Add custom</a>
                        </div>
                    </c:if>
                </div>
            </form>

            <div id="typeahead-target">
                <c:import url="fragments/parts/foodCardList.jsp"/>
            </div>
            <c:import url="fragments/footer.jsp"/>
        </div>


    </body>
</html>