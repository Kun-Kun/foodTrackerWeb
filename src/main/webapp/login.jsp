<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<c:import url="fragments/head.jsp" />
<body>
<div class="container">
    <c:import url="fragments/navbar.jsp" />
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Вхід до системи</h5>
                    <form class="form-signin" method="POST" action="/login">
                        <div class="form-label-group">
                            <input type="login" id="inputLogin" class="form-control" placeholder="Логін" required="true" autofocus="true" name="username" />
                            <label for="inputLogin">Логін</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="true" name="password" />
                            <label for="inputPassword">Пароль</label>
                        </div>
                        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Увійти</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




<div class="container">

 <%--   <form class="form-signin" action="/login">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputLogin" class="sr-only">Логин</label>
        <input id="inputLogin" class="form-control" placeholder="Логин" required="" autofocus="" name="user">
        <label for="inputPassword" class="sr-only">Пароль</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="" name="pwd">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <p class="mt-5 mb-3 text-muted">© 2017-2019</p>
    </form>
--%>
    <c:import url="fragments/footer.jsp" />
</div><!-- /.container -->
</body></html>