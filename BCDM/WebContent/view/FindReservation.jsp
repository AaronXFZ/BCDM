<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Reservation</title>
</head>
<body>
	<form action="getreservation" method="post">
		<table bgcolor="#ffffff" width="500" height="80" align="center">
			    <tr>
			      <td><h1>Find Your Reservation</h1></td>
			      <br>
			      
				  <td>
				  	<h4>Full Name:</h4>
				  	<input type="text" name="full_name">
				  </td>
				</tr>
				<tr>
				  	<td>
					    <h4>Phone Number:</h4>				  	
				  		<input type="text" name="phone_number">
				  	</td>
				</tr>
		</table>
		<table bgcolor="#ffffff" width="500" height="80" align="center" border="0">		
				<tr>
				  <td></td>
				  <td align="right"><input type="submit" name="Submit" value="Submit"></td>
				  <td></td>
				  <td><input type="reset" name="Clean" value="Clean"></td>
				  <td></td>
				</tr>  
		</table>
	</form>
</body>
</html>