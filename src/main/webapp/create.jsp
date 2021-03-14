<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление департамента</title>
</head>
<body>
    <h3>Новый департамент</h3>
    <form method="post" style="display:inline;">
        <label>Название</label><br>
        <input name="name" value="${name}"/><br><br>
        <input type="submit" value="Добавить" />
    </form> |
    <input type="button" onclick="history.back();" value="Отмена"/>
</body>
</html>