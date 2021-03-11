<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Department</title>
</head>
<body>
<h3>Edit Department</h3>
<form method="post">
<input type="hidden" value="${department.id}" name="id" />
<label>Name</label><br>
<input name="name" value="${department.name}" /><br><br>
<input type="submit" value="Send" />
</form>
</body>
</html>