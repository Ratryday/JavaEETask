<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование департамента</title>
</head>
<body>
    <h3>Редактирование департамента</h3>
    <form method="post" style="display:inline;">
        <input type="hidden" value="${department.id}" name="id" />
        <label>Название</label><br>
        <input name="name" value="${department.name}" /><br><br>
        <input type="submit" value="Сохранить" />
    </form> |
    <a href='<c:url value="/" />'>Отмена</a>
</body>
</html>