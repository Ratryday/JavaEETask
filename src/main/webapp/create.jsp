<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление департамента</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css" />
</head>
<body>
  <div class="box">
    <h2>Новый департамент</h2>
    <form class="button add table" method="post">
        <label>Название</label><br>
        <input type="text" name="name" value="${name}"/>
        <input type="submit" value="Добавить" />
    </form>
    <a href='<c:url value="/" />'>Отмена</a>
  </div>
</body>
</html>
