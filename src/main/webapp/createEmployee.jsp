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
        <input type="text" name="employeeName" value="${}"/><br><br>
        <label>Дата найма</label><br>
        <input type="date" name="hiringDate"/><br><br>
        <label>Опыт работы</label><br>
        <input type="number" name="experience"/><br><br>
        <label>Почтовый адрес</label><br>
        <input type="text" name="mailingAddress"/><br><br>
        <input type="hidden" name="departmentID" value="${param.id}"/><br><br>
        <input type="submit" value="Добавить" />
    </form> |
    <input type="button" onclick="history.back();" value="Отмена"/>
</body>
</html>