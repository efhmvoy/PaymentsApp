<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="payment.sort" var="paymentSortBy"/>
<fmt:message key="payment.sort.number" var="paymentSortByNumber"/>
<fmt:message key="payment.sort.date" var="paymentSortByDate"/>
<fmt:message key="payment.first.new" var="paymentFirstNew"/>
<fmt:message key="payment.first.old" var="paymentFirstOld"/>
<fmt:message key="payment.number" var="paymentNumber"/>
<fmt:message key="payment.amount" var="paymentAmount"/>
<fmt:message key="payment.receiver.number" var="paymentReceiverNumber"/>
<fmt:message key="payment.creation.date" var="paymentCreationDate"/>
<fmt:message key="payment.completion.date" var="paymentCompletionDate"/>


<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="sorting-menu">
    <h4>${paymentSortBy}</h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getExecutedPayments?sortBy=number&page=${executedPaymentsPage.page}">${paymentSortByNumber}</a></h4>
    <h4>${paymentSortByDate}</h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getExecutedPayments?sortBy=createTime&page=${executedPaymentsPage.page}">${paymentFirstNew}</a></h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getExecutedPayments?sortBy=createTimeDesc&page=${executedPaymentsPage.page}">${paymentFirstOld}</a></h4>
</div>

<table>
    <thead>
    <tr>
        <th>${paymentNumber}</th>
        <th>${paymentAmount}</th>
        <th>${paymentReceiverNumber}</th>
        <th>${paymentCreationDate}</th>
        <th>${paymentCompletionDate}</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${executedPaymentsPage.total>0}">
        <c:forEach var="payment" items="${executedPaymentsPage.paymentList}">
            <tr>
                <td>${payment.number}</td>
                <td>${payment.amount}</td>
                <td>${payment.recieverAccount}</td>
                <td><custom:LocalDateTag date="${payment.createTime}" /></td>
                <td><custom:LocalDateTag date="${payment.executeTime}" /></td>

            </tr>
        </c:forEach>


    </c:if>
    </tbody>
</table>

<div class="prepared-payments-pages">
    <c:if test="${executedPaymentsPage.total>0}">
        <c:forEach begin="0" end="${(Math.ceil(executedPaymentsPage.getTotal() /executedPaymentsPage.getPageSize()))-1}"
                   varStatus="i">
            <li class="${executedPaymentsPage.page ==  i.index ? "active" : ""}">
                <a href="${pageContext.request.contextPath}/paymentsApp/getExecutedPayments?page=${i.index}&size=5&sortBy=number">${i.index+1}</a>
            </li>
        </c:forEach>
    </c:if>
</div>
<p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
</body>


</html>