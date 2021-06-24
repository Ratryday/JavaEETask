<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css"/>
</head>
<body>
<div class="box">
    <h2>Список сотрудников ${departments.name}</h2>
    <div class="table">
        <form class="button add" method="get">
            <input type="hidden" name="command" value="CreateEmployee">
            <input type="hidden" name="departmentID" value="${departments.id}">
            <input type="submit" value="Добавить">
        </form>
        <form class="button" method="get">
            <input type="hidden" name="command" value="Index">
            <input type="submit" value="Список департаментов">
        </form>
    </div>
    <table class="table">
        <tr>
            <th>Имя</th>
            <th>Дата найма</th>
            <th>Опыт работы</th>
            <th>Почтовый адрес</th>
            <th></th>
        </tr>
        <c:forEach var="employees" items="${employee}">
            <tr>
                <td>${employees.employeeName}</td>
                <td>${employees.hiringDate}</td>
                <td>${employees.experience}</td>
                <td>${employees.mailingAddress}</td>
                <td>
                    <form class="button" method="get">
                        <input type="hidden" name="command" value="EditEmployee">
                        <input type="hidden" name="idEmployee" value="${employees.idEmployee}">
                        <input type="hidden" name="departmentID" value="${departments.id}">
                        <input type="submit" value="Редактировать">
                    </form>
                    <form class="button" method="post">
                        <input type="hidden" name="command" value="DeleteEmployee">
                        <input type="hidden" name="idEmployee" value="${employees.idEmployee}">
                        <input type="hidden" name="departmentID" value="${departments.id}">
                        <input type="hidden" name="departmentName" value="${departments.name}">
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
