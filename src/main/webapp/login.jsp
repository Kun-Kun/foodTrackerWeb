<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty subHeadline}"><c:set var="subHeadline" value="Login page" scope="request"/></c:if>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp" />
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp" />
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Please sign in</h5>
                    <form class="form-signin" method="POST" action="<c:url value = "/login"/>">
                        <c:if test="${not empty success}">
                            <div class="alert alert-success text-center" role="alert">
                                <c:out value="${success}"/>
                            </div>
                        </c:if>
                        <div class="form-label-group">
                            <label for="inputLogin">Login</label>
                            <input type="login" id="inputLogin" class="form-control" placeholder="Login" required="true" autofocus="true" name="user" />
                        </div>

                        <div class="form-label-group">
                            <label for="inputPassword">Password</label>
                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="true" name="pwd" />
                        </div>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger text-center" role="alert">
                                <c:out value="${error}" default="Помилка. Неправильний логін або пароль"/>
                            </div>
                        </c:if>

                        <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
                        <input type="hidden" name="origin" value="${origin}"/>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




<div class="container">
    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>