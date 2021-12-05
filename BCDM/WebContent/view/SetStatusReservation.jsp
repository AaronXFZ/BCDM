<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>"${requestScope.Status}"</title>
</head>
<body>
	<h1 style="color: black;"> Your Order Status: <h1 style="color: black;"><c:out value="${requestScope.Status}!" /></h1></h1>
	<br>
	
	<form action="setreservation" method="post">
		<table bgcolor="#ffffff" width="500" height="80" align="center">

			
			<tr>
			  <td><h1><c:out value="Confirm Your Order: "></c:out></h1></td>
			  <td>
			  	  <p>Enter Your Credentials to Confirm Your Identity</p>
				  <tr>
				  <td><h2>Full name:</h2></td>
			      <td><c:out value="Full Name "></c:out></td>
				  <td><input type="text" name="full_name" required></td>
				</tr>
				<tr>
				  <td><h2>Phone Number:</h2></td>
				  <td><c:out value="Phone Number: "></c:out></td>
				  <td><input type="text" name="phone_number" required></td>
				</tr>
			  
				  <p>Check to confirm you're here.</p>
				  <input type="checkbox" id="customer_confirmation" name="customer_confirmation" value="customerHere" required>
				  <label for="customer_confirmation"> I am here.</label><br>
				  

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