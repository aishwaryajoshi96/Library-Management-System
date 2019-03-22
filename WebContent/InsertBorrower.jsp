<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Enter Borrower Details</title>
</head>
<body>
	<form action="InsertBorrower">

		<table style="width: 100%">

			<tr>
				<td width="168"><b>SSN</span></b></td>
				<td><input type="text" name="Ssn"></td>
			</tr>
			<tr>
				<td width="168"><b>FNAME</b></td>
				<td><input type="text" name="First_Name"></td>
			</tr>
			<tr>
				<td width="168"><b>LNAME</b></td>
				<td><input type="text" name="Last_Name"></td>
			</tr>
			<tr>
				<td width="168"><b>ADDRESS</b></td>
				<td><input type="text" name="Address"></td>
			</tr>
			<tr>
				<td width="168"><b>PHONE</b></td>
				<td><input type="text" name="Phone"></td>
			</tr>
		</table>
		<input type="submit" value="Submit" onclick="validate()">
	
	</form>
</body>
</html>