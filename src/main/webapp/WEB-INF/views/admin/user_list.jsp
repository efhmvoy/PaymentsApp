<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="user.firstname" var="userFirstName"/>
<fmt:message key="user.lastname" var="userLastName"/>
<fmt:message key="user.login" var="userLogin"/>
<fmt:message key="user.status" var="userStatus"/>
<fmt:message key="user.block" var="userBlock"/>
<fmt:message key="user.unblock" var="userUnblock"/>

<html>

<head>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

<body>
<%@ include file="/WEB-INF/views/components/header.jsp" %>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>${userFirstName}</th>
        <th>${userLastName}</th>
        <th>${userLogin}</th>
        <th>${userStatus}</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

        <c:forEach var="user" items='${sessionScope.get("userList")}'>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.login}</td>
                <td>${user.userStatus.name}</td>
                <td><a href="${pageContext.request.contextPath}/paymentsApp/updateUserStatus?userId=${user.id}&userStatus=2">${userBlock}</a></td>
                <td><a href="${pageContext.request.contextPath}/paymentsApp/updateUserStatus?userId=${user.id}&userStatus=1">${userUnblock}</a></td>
            </tr>
        </c:forEach>



    </tbody>
</table>
</body>


</html>