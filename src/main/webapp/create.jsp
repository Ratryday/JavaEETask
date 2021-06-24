<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление департамента</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css"/>
</head>
<body>
<div class="box">
    <h2>Новый департамент</h2>
    <form class="button add table" method="post">
        <label>Название</label><br>
        <input class="box" type="text" name="name" value="${name}"/>
        <input type="hidden" name="command" value="CreateDepartment">
        <input type="submit" value="Добавить"/>
    </form>
    <form method="get">
        <input type="hidden" name="command" value="Index"/>
        <input type="submit" value="Отмена"/>
    </form>
</div>
</body>
</html>
