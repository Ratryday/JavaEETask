<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Department</title>
</head>
<body>
<h2>Department List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
<tr><tr>Name</tr></th>
<c:forEach var="departments" items="${department}">
 <tr><td>${departments.name}</td>
    <td>
    <a href='<c:url value="/edit?id=${departments.id}" />'>Edit</a> |
    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
        <input type="hidden" name="id" value="${departments.id}">
        <input type="submit" value="Delete">
    </form> |
    <a href='<c:url value="/employeeList?id=${departments.id}&departmentName=${departments.name}" />'>Employees List</a>
 </td></tr>
</c:forEach>
</table>
</body>
</html>