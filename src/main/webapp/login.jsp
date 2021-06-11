<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="login.login" var="loginLogin"/>
<fmt:message key="login.password" var="loginPassword"/>
<fmt:message key="login.log.in" var="loginLogIn"/>
<fmt:message key="login.home" var="loginHome"/>
<fmt:message key="login.register" var="loginRegister"/>

<html>
<head>
    <title>Login</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<div class="login-page">
<div class="login">
    <form method="post" action="${pageContext.request.contextPath}/paymentsApp/login">
        <p class="center-align title">${loginLogin}</p>
        <div class="row">
            <div class="input-field col s12">
                <input id="login" type="text" class="validate" name="login">
                <label for="login">${loginLogin}</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <input id="password" type="password" class="validate" name="password">
                <label for="password">${loginPassword}</label>
            </div>
        </div>
        <div class="row">
            <button class="btn waves-effect waves-light login-button" type="submit" name="action">${loginLogIn}
                <i class="material-icons right">send</i>
            </button>
        </div>
        <div class="login-links">
            <a href="${pageContext.request.contextPath}/">${loginHome}</a>
            <a href="${pageContext.request.contextPath}/register.jsp">${loginRegister}</a>
        </div>
        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </form>
</div>
</div>
<%@ include file="/WEB-INF/views/components/footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>