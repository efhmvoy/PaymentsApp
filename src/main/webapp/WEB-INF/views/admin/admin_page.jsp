<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="user.hello" var="userHello"/>
<fmt:message key="user.users" var="userUsers"/>

<html>
<head>
    <title>Admin page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="content">

    <div class="main-content">
        <div class="greetings">
            <h2>${userHello} ${sessionScope.login != null ? sessionScope.login : ""}</h2>

        </div>

        <div class="accounts-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/getUserList">${userUsers}</a>
        </div>
    </div>
</div>
</body>
</html>