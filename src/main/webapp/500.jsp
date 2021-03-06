<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="Main page"  scope="request"/></c:if>
    <!DOCTYPE html>
    <html lang="en">
    <c:import url="fragments/head.jsp" />
    <body>
        <div class="container">
            <c:import url="fragments/navbar.jsp" />
            <div class="page-wrap d-flex flex-row align-items-center">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-12 text-center">
                            <span class="display-1 d-block">500</span>
                            <div class="mb-4 lead">There was an error on the server side, your best bet is to try again, or mail support</div>
                            <a href="<c:url value = "/"/>" class="btn btn-link">Back to Home</a><a href="mailto:name@email.com" class="btn btn-link">Support</a>
                        </div>
                    </div>
                </div>
            </div>
            <c:import url="fragments/footer.jsp" />
        </div><!-- /.container -->
    </body></html>