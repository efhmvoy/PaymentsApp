<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="error.notFound" var="errorNotFound"/>
<fmt:message key="error.permission.denied" var="errorPermissionDenied"/>
<fmt:message key="error.return.main" var="errorReturnMain"/>

<html>
<head>
    <title>Error page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="error_page">
        <h1>${errorNotFound}</h1>
        <p>${errorPermissionDenied}</p>
        <a href="/">${errorReturnMain}</a>
    </div>
</div>
</body>
</html>