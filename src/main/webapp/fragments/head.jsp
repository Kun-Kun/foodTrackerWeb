<%--
  Created by IntelliJ IDEA.
  User: Я
  Date: 16.06.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <title><c:out value="${headline}" default="Food tracker"/><c:if test="${not empty subHeadline}"><c:out value=" - ${subHeadline}"/></c:if></title>

    <link href="<c:url value = "/static/css/bootstrap.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value = "/static/css/tempusdominus-bootstrap-4.min.css"/>" rel="stylesheet"/>
    <link href="<c:url value = "/static/css/style.css"/>" rel="stylesheet"/>
    <link href="<c:url value = "/static/css/tabulator.min.css"/>" rel="stylesheet" />
    <link href="<c:url value = "/static/css/typeaheadjs.css"/>" rel="stylesheet" />
    <script type="text/javascript" src="<c:url value = "/static/js/jquery-3.4.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/bootstrap.bundle.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/awesome.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/moment-with-locales.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/tempusdominus-bootstrap-4.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/typeahead/typeahead.bundle.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/typeahead/bloodhound.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/typeahead/typeahead.jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value = "/static/js/tabulator.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value = "/static/js/main.js"/>"></script>
    <script th:inline="javascript" type="text/javascript">
        var _csrf = "${csrf_token}";
        var _csrf_header = "${csrf_field_name}";
        var context_path = "<c:url value = "/"/>";
    </script>

</head>