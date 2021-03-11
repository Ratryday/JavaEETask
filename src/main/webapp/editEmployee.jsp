<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
</head>
<body>
    <h3>Edit Employee</h3>
    <form method="post">
        <label>Name</label><br>
        <input name="name" value="${employee.employeeName}" /><br>
        <label>Hiring Date</label><br>
        <input type="date" name="hiringDate" value="${employee.hiringDate}" /><br>
        <label>Experience</label><br>
        <input name="experience" value="${employee.experience}" /><br>
        <label>Mailing Address</label><br>
        <input name="mailingAddress" value="${employee.mailingAddress}" /><br>
        <label>Department</label><br>
        <select name="departmentId" >
           <c:forEach items="${department}" var="departments">
               <option value="${departments.id}">${departments.name}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="idEmployee" value="${employee.idEmployee}"/><br>
        <input type="hidden" name="departmentName" value="${departmentName.name}"/><br>
        <input type="hidden" name="departmentID" value="${departmentID}"/>

        <input type="submit" value="Send" />
    </form>
</body>
</html>