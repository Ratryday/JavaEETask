<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление сотрудника</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css"/>
</head>
<body>
<div class="box">
    <h2>Новый сотрудник</h2>
    <form class="button add table" method="post">
        <label>Имя</label><br>
        <input type="text" name="employeeName" value="${employee.employeeName}"/><br><br>
        <label>Дата найма</label><br>
        <input type="date" name="hiringDate" value="${employee.hiringDate}"/><br><br>
        <label>Опыт работы</label><br>
        <input type="text" name="experience" value="${employee.experience}"/><br><br>
        <label>Почтовый адрес</label><br>
        <input type="email" name="mailingAddress" value="${employee.mailingAddress}"/><br><br>
        <input type="hidden" name="departmentID" value="${departmentID}"/><br><br>
        <input type="hidden" name="command" value="CreateEmployee">
        <input type="submit" value="Добавить"/>
    </form>
    <form method="get">
        <input type="hidden" name="command" value="GetEmployeeList"/>
        <input type="hidden" name="id" value="${departmentID}"/>
        <input type="submit" value="Отмена"/>
    </form>
</div>
</body>
</html>
