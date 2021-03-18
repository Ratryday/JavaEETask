<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование сотрудника</title>
</head>
<body>
    <h3>Редактирование сотрудника</h3>
    <form method="post" style="display:inline;">
        <label>Имя</label><br>
        <input name="name" value="${employee.employeeName}" /><br>
        <label>Дата найма</label><br>
        <input type="date" name="hiringDate" value="${employee.hiringDate}" /><br>
        <label>Опыт работы</label><br>
        <input name="experience" value="${employee.experience}" /><br>
        <label>Почтовый адрес</label><br>
        <input name="mailingAddress" value="${employee.mailingAddress}" /><br>
        <label>Департамент</label><br>
        <select name="departmentId">
        <option selected value="${department.id}" hidden="">${department.name}</option>
           <c:forEach items="${departments}" var="dep">
               <option value="${dep.id}">${dep.name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="idEmployee" value="${employee.idEmployee}"/><br>
        <input type="hidden" name="oldDepartmentID" value="${department.id}"/><br><br>
        <input type="submit" value="Сохранить" />
    </form> |
    <a href='<c:url value="/employeeList?id=${department.id}" />'>Отмена</a>
</body>
</html>