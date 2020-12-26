<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>SBB</title>
</head>

<body>
    <h1><%= "SBB Project" %></h1>

    <hr>
    <p>
        Username: <security:authentication property="principal.username"/>
    </p>
    <p>
        Role: <security:authentication property="principal.authorities" />
    </p>

    <hr>
    <security:authorize access="hasRole('ADMIN')">
    <p>
        <a href ="${pageContext.request.contextPath}/system/info">System page</a>
        (only for Admin)
    </p>

    </security:authorize>

    <hr>

<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <div style="margin-top: 10px" class="form-group">
        <div class="col-sm-6 controls">
            <button type="submit" class="btn btn-success">Logout</button>
        </div>
    </div>

</form:form>

</body>

</html>