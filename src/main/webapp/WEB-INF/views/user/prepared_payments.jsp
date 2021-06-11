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
<fmt:message key="payment.execute" var="paymentExecute"/>

<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<div class="sorting-menu">
    <h4>${paymentSortBy}</h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getPreparedPayments?sortBy=number&page=${preparedPaymentsPage.page}">${paymentSortByNumber}</a></h4>
    <h4>${paymentSortByDate}</h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getPreparedPayments?sortBy=createTime&page=${preparedPaymentsPage.page}"> ${paymentFirstNew}</a></h4>
    <h4><a href="${pageContext.request.contextPath}/paymentsApp/getPreparedPayments?sortBy=createTimeDesc&page=${preparedPaymentsPage.page}">${paymentFirstOld}</a></h4>
</div>

    <table>
        <thead>
        <tr>
            <th>${paymentNumber}</th>
            <th>${paymentAmount}</th>
            <th>${paymentReceiverNumber}</th>
            <th>${paymentCreationDate}</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${preparedPaymentsPage.total>0}">
            <c:forEach var="payment" items="${preparedPaymentsPage.paymentList}">
                    <tr>
                        <td>${payment.number}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.recieverAccount}</td>
                        <td> <custom:LocalDateTag date="${payment.createTime}"/> </td>
                        <td><a href="${pageContext.request.contextPath}/paymentsApp/executePayment?paymentNumber=${payment.number}">${paymentExecute}</a></td>

                    </tr>
            </c:forEach>


        </c:if>
        </tbody>
    </table>

<div class="prepared-payments-pages">
    <c:if test="${preparedPaymentsPage.total>0}">
    <c:forEach begin="0" end="${(Math.ceil(preparedPaymentsPage.getTotal() /preparedPaymentsPage.getPageSize()))-1}"
               varStatus="i">
        <li class="${preparedPaymentsPage.page ==  i.index ? "active" : ""}">
            <a href="${pageContext.request.contextPath}/paymentsApp/getPreparedPayments?page=${i.index}&size=5&sortBy=number">${i.index+1}</a>
        </li>
    </c:forEach>
    </c:if>
</div>

</body>


</html>