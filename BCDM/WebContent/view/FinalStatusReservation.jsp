<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${requestScope.Status}</title>
</head>
<body>
	<h1>Your Status is: </h1>
	<h1 style="color: black;"> <c:out value="${requestScope.Status}!" /></h1>
</body>
</html>