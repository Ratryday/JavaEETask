<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Департаменты</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css" />
</head>
<body>
    <div class="box">
<h2>Список департаментов</h2>
 <form class="button add" method="get">
        <input type="hidden" name="command" value="CreateDepartment">
        <input type="submit" value="Добавить">
    </form>
<table  class="table" class="button">
<tr><th>Название</th></tr>
<c:forEach var="department" items="${departments}">
 <tr class="depart"><td class="departmentName">${department.name}</td>
    <td>
    <form class="button" method="get" action='<c:url value="/edit" />' style="display:inline;">
        <input type="hidden" name="id" value="${department.id}">
        <input type="submit" value="Редактировать">
    </form>
    <form class="delete" method="post" action='<c:url value="/delete" />' style="display:inline;">
        <input type="hidden" name="id" value="${department.id}">
        <input type="submit" value="Удалить">
    </form>
    <form class="button" method="get" action='<c:url value="/employeeList" />' style="display:inline;">
        <input type="hidden" name="id" value="${department.id}">
        <input type="submit" value="Список сотрудников">
    </form>
 </td></tr>
</c:forEach>
</table>
</div>
</body>
</html>
