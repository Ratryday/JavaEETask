<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>
<h2>${departmentName} Employee</h2>
<p><a href='<c:url value="/createEmployee?id=${departmentID}&departmentName=${departmentName}" />'>Create new</a></p>
<table>
<tr><th>Name</th><th>Hiring Date</th><th>Experience</th><th>Mailing Address</th><th></th></tr>
<c:forEach var="employees" items="${employee}">
 <tr><td>${employees.employeeName}</td>
    <td>${employees.hiringDate}</td>
            <td>${employees.experience}</td>
                    <td>${employees.mailingAddress}</td>
                        <td>
    <a href='<c:url value="/editEmployee?id=${employees.idEmployee}&departmentID=${departmentID}" />'>Edit</a> |
    <form method="post" action='<c:url value="/deleteEmployee" />' style="display:inline;">
        <input type="hidden" name="idEmployee" value="${employees.idEmployee}">
        <input type="hidden" name="departmentID" value="${departmentID}">
        <input type="hidden" name="departmentName" value="${departmentName}">
        <input type="submit" value="Delete">
    </form>
 </td></tr>
</c:forEach>
</table>
</body>
</html>