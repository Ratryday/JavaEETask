<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Департаменты</title>
</head>
<body>
<h2>Список департаментов</h2>
 <form method="get" action='<c:url value="/create" />'>
        <input type="submit" value="Добавить">
    </form>
<table>
<tr><th>Название</th></tr>
<c:forEach var="departments" items="${department}">
 <tr><td>${departments.name}</td>
    <td>
    <form method="get" action='<c:url value="/edit" />' style="display:inline;">
        <input type="hidden" name="id" value="${departments.id}">
        <input type="submit" value="Редактировать">
    </form> |
    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
        <input type="hidden" name="id" value="${departments.id}">
        <input type="submit" value="Удалить">
    </form> |
    <form method="get" action='<c:url value="/employeeList" />' style="display:inline;">
        <input type="hidden" name="id" value="${departments.id}">
        <input type="submit" value="Список сотрудников">
    </form>
 </td></tr>
</c:forEach>
</table>
</body>
</html>