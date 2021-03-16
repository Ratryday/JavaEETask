<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
</head>
<body>
<h2>Список сотрудников ${department.name}</h2>
    <form method="get" action='<c:url value="/createEmployee" />' style="display:inline;">
        <input type="hidden" name="departmentName" value="${department.name}">
        <input type="hidden" name="departmentID" value="${department.id}">
        <input type="submit" value="Добавить">
    </form> |
    <form method="get" action='<c:url value="/" />' style="display:inline;">
        <input type="submit" value="Список департаментов">
    </form>
<table>
<tr><th>Имя</th><th>Дата найма</th><th>Опыт работы</th><th>Почтовый адрес</th><th></th></tr>
<c:forEach var="employees" items="${employee}">
 <tr><td>${employees.employeeName}</td>
    <td>${employees.hiringDate}</td>
            <td>${employees.experience}</td>
                    <td>${employees.mailingAddress}</td>
                        <td>
    <form method="get" action='<c:url value="/editEmployee" />' style="display:inline;">
        <input type="hidden" name="idEmployee" value="${employees.idEmployee}">
        <input type="hidden" name="departmentID" value="${department.id}">
        <input type="submit" value="Редактировать">
    </form> |
    <form method="post" action='<c:url value="/deleteEmployee" />' style="display:inline;">
        <input type="hidden" name="idEmployee" value="${employees.idEmployee}">
        <input type="hidden" name="departmentID" value="${department.id}">
        <input type="hidden" name="departmentName" value="${department.name}">
        <input type="submit" value="Удалить">
    </form>
 </td></tr>
</c:forEach>
</table>
</body>
</html>