<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css"/>
</head>
<body>
<div class="box">
    <h2>Добро пожаловать</h2>
    <a class="button" href="/JavaEETest/front-controller/?command=Index" />Начать</a>
</div>
</body>
</html>
