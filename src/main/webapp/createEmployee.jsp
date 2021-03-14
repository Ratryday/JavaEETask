<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление сотрудника</title>
</head>
<body>
    <h3>Новый сотрудник</h3>
    <form method="post" style="display:inline;">
        <label>Имя</label><br>
        <input name="employeeName" value="${employee.employeeName}" /><br><br>
        <label>Дата найма</label><br>
        <input type="date" name="hiringDate" value="${employee.hiringDate}" /><br><br>
        <label>Опыт работы</label><br>
        <input name="experience" value="${employee.experience}" /><br><br>
        <label>Почтовый адрес</label><br>
        <input name="mailingAddress" value="${employee.mailingAddress}" /><br><br>
        <input type="hidden" name="departmentID" value="${param.id}"/><br><br>
        <input type="submit" value="Добавить" />
    </form> |
    <input type="button" onclick="history.back();" value="Отмена"/>
</body>
</html>