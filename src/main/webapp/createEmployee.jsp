<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create employee</title>
</head>
<body>
<h3>New employee</h3>
<form method="post">
<label>Name</label><br>
<input type="text" name="employeeName"/><br><br>
<label>Hiring Date</label><br>
<input type="date" name="hiringDate"/><br><br>
<label>Experience</label><br>
<input type="number" name="experience"/><br><br>
<label>Mailing Address</label><br>
<input type="text" name="mailingAddress"/><br><br>
<input type="hidden" name="departmentID" value="${departmentID}"/><br><br>
<input type="submit" value="Save" />
</form>
</body>
</html>