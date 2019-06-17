<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/head.jsp" />
<body>
<div class="container">
    <jsp:include page="fragments/navbar.jsp" />

    <div class="row">
        <div class="col-md-12 blog-main">
            <h3 class="pb-4 mb-4 font-italic border-bottom">
                Register new user
            </h3>
            <form action="/register" method="POST">
                <div class="form-group row">
                    <label for="inputLogin" class="col-sm-3 col-form-label">Login</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputLogin" placeholder="Login" name="login" value="<c:out value="${login}" />"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputEmail" class="col-sm-3 col-form-label">E-mail</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="inputEmail" placeholder="E-mail" name="email"  value="<c:out value="${email}" />">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputConfirmPassword" class="col-sm-3 col-form-label">Confirm password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="inputConfirmPassword" placeholder="Confirm password" name="confirmPassword">
                    </div>
                </div>
                <input type="hidden" name="${csrf_field_name}" value="${csrf_token}"/>
                <div class="form-group row">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary">Sign up</button>
                    </div>
                </div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center" role="alert">
                        <c:out value="${error}" default="Помилка."/>
                    </div>
                </c:if>
            </form>
        </div>

    </div><!-- /.row -->
    <jsp:include page="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>