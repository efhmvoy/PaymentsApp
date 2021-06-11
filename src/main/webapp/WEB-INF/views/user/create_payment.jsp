<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="payment.amount" var="paymentAmount"/>
<fmt:message key="payment.receiver.number" var="paymentReceiverNumber"/>
<fmt:message key="payment.create" var="paymentCreate"/>

<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="create-payment-page">
    <div class="payment">
        <form method="post" action="${pageContext.request.contextPath}/paymentsApp/createPayment">
            <div class="row">
                <div class="input-field col s12">
                    <input id="amount" type="text" maxlength="8" class="validate" name="amount">
                    <label for="amount">${paymentAmount}</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="receiverAccount" type="text" maxlength="8" class="validate" name="receiverAccount">
                    <label for="receiverAccount">${paymentReceiverNumber}</label>
                </div>
            </div>
            <div class="row">
                <button class="btn waves-effect waves-light login-button" type="submit" name="action">${paymentCreate}
                    <i class="material-icons right">send</i>
                </button>
            </div>
            <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
            <p class="center-align green-text text-darken-2 successful__message">${requestScope.get("successMessage")}</p>
        </form>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>

</html>