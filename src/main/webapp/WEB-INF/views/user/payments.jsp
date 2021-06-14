<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="account.add.funds" var="accountAddFunds"/>
<fmt:message key="payment.create" var="paymentCreate"/>
<fmt:message key="payment.executed" var="paymentExecuted"/>
<fmt:message key="payment.prepared" var="paymentPrepared"/>
<fmt:message key="account.block" var="accountBlock"/>

<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
    <div class="payments-content">
        <div class="executed-payments-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/getExecutedPayments?sortBy=number">${paymentExecuted}</a>
        </div>
        <div class="prepared-payments-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/getPreparedPayments?sortBy=number">${paymentPrepared}</a>
        </div>
        <div class="create-payment-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/getCreatePaymentPage">${paymentCreate}</a>
        </div>
        <div class="create-payment-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/getAddFundsPage">${accountAddFunds}</a>
        </div>
        <div class="create-payment-link">
            <a href="${pageContext.request.contextPath}/paymentsApp/blockAccount">${accountBlock}</a>
        </div>
    </div>
</body>
</html>