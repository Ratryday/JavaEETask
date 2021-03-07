<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>
<h2>${departmentsName} Employee</h2>
<p><a href='<c:url value="/createEmployee?id=${departmentID}" />'>Create new</a></p>
<table>
<tr><tr>Name</tr></th>
<c:forEach var="employees" items="${employee}">
 <tr><td>${employees.employeeName}</td>
    <td>
    <a href='<c:url value="/editEmployee?id=${employees.idEmployee}" />'>Edit</a> |
    <form method="post" action='<c:url value="/deleteEmployee" />' style="display:inline;">
        <input type="hidden" name="id" value="${employees.idEmployee}">
        <input type="submit" value="Delete">
    </form>
 </td></tr>
</c:forEach>
</table>
</body>
</html>