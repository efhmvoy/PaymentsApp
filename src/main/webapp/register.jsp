<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/components/lib.jsp" %>

<fmt:message key="login.login" var="loginLogin"/>
<fmt:message key="login.password" var="loginPassword"/>
<fmt:message key="user.firstname" var="userFirstName"/>
<fmt:message key="user.lastname" var="userLastName"/>
<fmt:message key="button.registration" var="buttonRegister"/>


<html>

<head>
    <title>Registration</title>
    <%@ include file="/WEB-INF/views/components/head.jsp" %>
</head>

  <body>
  <div class="register-page">
      <div class="register-content">
          <div class="row">
              <form  class="col s12 register-form" method="post" action="${pageContext.request.contextPath}/paymentsApp/register">

                      <div class="input-field col s12">
                          <input  id="first_name" type="text" class="validate" name="first_name">
                          <label for="first_name">${userFirstName}</label>
                      </div>
                      <div class="input-field col s12">
                          <input id="last_name" type="text" class="validate" name="last_name">
                          <label for="last_name">${userLastName}</label>
                      </div>



                      <div class="input-field col s12">
                          <input id="login" type="text" class="validate" name="login">
                          <label for="login">${loginLogin}</label>
                      </div>
                      <div class="input-field col s12">
                          <input id="password" type="password" class="validate" name="password">
                          <label for="password">${loginPassword}</label>
                      </div>

                  <div class="row">
                      <button class="btn waves-effect waves-light" type="submit" name="action">${buttonRegister}
                          <i class="material-icons right">send</i>
                      </button>
                  </div>
                  <p class="center-align red-text text-darken-2 warning__message">${requestScope.get("errorMessage")}</p>
                  <p class="center-align green-text text-darken-2 successful__message"></p>
              </form>
          </div>
      </div>
  </div>
  <%@ include file="/WEB-INF/views/components/footer.jsp" %>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
  </body>
</html>