<fmt:message key="header.login" var="headerLogin"/>
<fmt:message key="header.logout" var="headerLogout"/>

<header>
    <nav>
        <div class="nav-wrapper header">
            <a  class="brand-logo">Payments application</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/paymentsApp/changeLocale?lang=ua">UA</a></li>
                <li><a href="${pageContext.request.contextPath}/paymentsApp/changeLocale?lang=en">EN</a></li>
                <li><a href="/paymentsApp/${sessionScope.role.equals("USER") ? "getUserPage" : "getAdminPage"}">
                    ${sessionScope.login != null ? sessionScope.login : ""}
                </a>
                </li>
                <li>
                    <a class="dropdown-trigger"
                       href="${sessionScope.login != null ? "/paymentsApp/logout" : "/login.jsp"}"
                       data-target="dropdown1">
                        ${sessionScope.login != null ? headerLogout : headerLogin}
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
