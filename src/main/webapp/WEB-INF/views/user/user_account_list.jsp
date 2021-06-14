<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="account.create" var="accountCreate"/>
<fmt:message key="account.sort" var="accountSort"/>
<fmt:message key="account.sort.number" var="accountsortNumber"/>
<fmt:message key="account.sort.name" var="accountsortName"/>
<fmt:message key="account.sort.balance" var="accountsortBalance"/>
<fmt:message key="account.balance" var="accountBalance"/>
<fmt:message key="account.name" var="accountName"/>
<fmt:message key="account.status" var="accountStatus"/>
<fmt:message key="account.number" var="accountNumber"/>


<html>
<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>
    <div class="account-menu">
        <div class="create-account">
            <h2><a class="waves-effect waves-light btn modal-trigger" href="#modal1">${accountCreate}</a> </h2>
            <div id="modal1" class="modal">
                <form  class="col s12 register-form" method="post" action="${pageContext.request.contextPath}/paymentsApp/createUserAccount">
                <div class="modal-content">
                        <div class="input-field col s12">
                            <input  id="first_name" type="text" class="validate" name="name">
                            <label for="first_name">${accountName}</label>
                        </div>
                </div>
                <div class="modal-footer">
                    <button class=" modal-close btn waves-effect waves-light" type="submit" name="action">${accountCreate}
                        <i class="material-icons right">send</i>
                    </button>
                </div>
                </form>
            </div>
        </div>

        <div class="sorting-menu">
            <h4>${accountSort}</h4>
            <h4><a href="${pageContext.request.contextPath}/paymentsApp/getAccountListSorted?sortValue=number">${accountsortNumber}</a></h4>
            <h4><a href="${pageContext.request.contextPath}/paymentsApp/getAccountListSorted?sortValue=name">${accountsortName}</a></h4>
            <h4><a href="${pageContext.request.contextPath}/paymentsApp/getAccountListSorted?sortValue=balance">${accountsortBalance}</a></h4>
        </div>
    </div>
<p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
    <div class="account-list-content">

       <c:forEach var="account" items='${sessionScope.get("accountList")}'>
           <div class="account-item">
               <div>${accountNumber}: ${account.number}</div>
               <div>${accountName}:  ${account.name}</div>
               <div>${accountBalance}: ${account.balance}</div>
               <div>${accountStatus}: ${account.accountStatus.name}</div>
               <a href="${pageContext.request.contextPath}/paymentsApp/getPaymentsPage?accountNumber=${account.number}&accountStatus=${account.accountStatus.name}" class="account-item-link"></a>
           </div>

       </c:forEach>


    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script >
        document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems, {});
        });
    </script>
</body>
</html>