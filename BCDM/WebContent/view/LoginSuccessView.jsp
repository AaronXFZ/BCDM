<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User Authentication</title>
	</head>

	<body>

                
	<form action="reservation" method="post">
		<table bgcolor="#ffffff" width="500" height="80" align="center">
			    <tr>
			      <tr>
			      <td><c:out value="Enter Name: "></c:out></td>
				  <td><input type="text" name="fullname"></td>
				</tr>
				<tr>
			      <tr>
			      <td><c:out value="Enter Phone Number: "></c:out></td>
				  <td><input type="text" name="phone_number"></td>
				</tr>
			
				<tr>
				  <td><c:out value="Enter the following : "></c:out></td>
				  <td>
					  <p>Check the Items You Want:</p>
					  <input type="checkbox" id="Cheeseburger" name="food_items" value="Cheeseburger">
					  <label for="Cheeseburger"> Cheeseburger $4.00</label><br>
					  
					  <input type="checkbox" id="Chicken Taco" name="food_items" value="Chicken Taco">
					  <label for="Chicken Taco"> Chicken Taco $3.00</label><br>
					  
		
					  <input type="checkbox" id="Beef Taco" name="food_items" value="Beef Taco">
					  <label for="vehicle3"> Beef Taco $2.00</label><br>
					  
					  
					  <input type="checkbox" id="Seafood Gumbo" name="food_items" value="Seafood Gumbo">
					  <label for="Seafood Gumbo"> Seafood Gumbo $10.00</label><br>
					  
		
					  <input type="checkbox" id="Snickers" name="food_items" value="Snickers">
					  <label for="Chicken Taco"> Snickers $2.00</label><br>
		  
					  <input type="checkbox" id="Slice of Cake" name="food_items" value="Slice of Cake">
					  <label for="Cheeseburger"> Slice of Cake $3.00</label><br>
					  
		
					  <input type="checkbox" id="Cotton Candy" name="food_items" value="Cotton Candy">
					  <label for="Chicken Taco"> Cotton Candy $3.00</label><br>
					  
		
					  <input type="checkbox" id="Bottled Water" name="food_items" value="Bottled Water">
					  <label for="vehicle3"> Bottled Water $4.00</label><br>
					  
					  
					  <input type="checkbox" id="Fountain Drink" name="food_items" value="Fountain Drink">
					  <label for="Seafood Gumbo"> Fountain Drink $2.00</label><br>
					  
		
					  <input type="checkbox" id="Coffee" name="food_items" value="Coffee">
					  <label for="Chicken Taco"> Coffee $1.00</label><br>
					  
					  <input type="checkbox" id="Milk" name="food_items" value="Milk">
					  <label for="Cheeseburger"> Slice of Cake $3.00</label><br>
					  
		
					  <input type="checkbox" id="Milkshake" name="food_items" value="Milkshake">
					  <label for="Chicken Taco"> Cotton Candy $3.00</label><br>
					  
		
					  <input type="checkbox" id="Boiled Egg" name="food_items" value="Boiled Egg">
					  <label for="vehicle3"> Boiled Egg $1.00</label><br>
					  
					  
					  <input type="checkbox" id="Hotdog" name="food_items" value="Hotdog">
					  <label for="Seafood Gumbo"> Hotdog $2.00</label><br>
					  
		
					  <input type="checkbox" id="Fried Rice" name="food_items" value="Fried Rice">
					  <label for="Chicken Taco"> Fried Rice $8.00</label><br><br>
					  
					  <input type="checkbox" id="online_reservation" name="reserve_type" value="online_reservation">
					  <label for="online_reservation"> Mark if this is an online order.</label><br><br>
			
					  
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