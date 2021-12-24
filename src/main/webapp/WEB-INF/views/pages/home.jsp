<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<link rel="stylesheet" href="<c:url value='/webjars/bootstrap/css/bootstrap.min.css' />" />
<title>home</title>
</head>
<body>
    <div class="container">
        <sec:authentication property="principal.user" var="currentUser" scope="session" />
        <h1>Welcome ${currentUser.displayName} | <a href="<c:url value='/logout'/>">Logout</a></h1>
    </div>
</body>
</html>