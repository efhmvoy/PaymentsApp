<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>


<fmt:message key="account.number" var="accountNumber"/>
<fmt:message key="account.status" var="accountStatus"/>
<fmt:message key="user.unblock" var="accountUnblock"/>

<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
<table>
    <thead>
    <tr>
        <th>${accountNumber}</th>
        <th>${accountStatus}</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="account" items='${sessionScope.get("blockedAccountList")}'>
        <tr>
            <td>${account.number}</td>
            <td>${account.accountStatus.name}</td>
            <td><a href="${pageContext.request.contextPath}/paymentsApp/unblockAccount?accountNumber=${account.number}">${accountUnblock}</a></td>
        </tr>
    </c:forEach>



    </tbody>
</table>
<p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
</body>
</html>