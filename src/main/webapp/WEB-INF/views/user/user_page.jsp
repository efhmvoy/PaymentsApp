<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="user.hello" var="userHello"/>
<fmt:message key="users.accounts" var="userAccounts"/>


<html>
<head>
    <title>User page</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<div class="content">

    <div class="user-main-content">
            <div class="greetings">
                <h2>${userHello} ${sessionScope.login != null ? sessionScope.login : ""}</h2>

            </div>

            <div class="accounts-link">
                <a href="${pageContext.request.contextPath}/paymentsApp/getAccountList">${userAccounts}</a>
            </div>
        </div>




        <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
        <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
    </div>
</div>
</body>
</html>