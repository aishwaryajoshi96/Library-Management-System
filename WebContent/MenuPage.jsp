<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
body {
	font-family: "Lato", sans-serif;
}

.sidenav {
	display: none;
	height: 100%;
	width: 250px;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	background-color: #111;
	overflow-x: hidden;
	padding-top: 60px;
}

.sidenav a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
}

.sidenav a:hover {
	color: #f1f1f1;
}

.sidenav .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
}

@media screen and (max-height: 450px) {
	.sidenav {
		padding-top: 15px;
	}
	.sidenav a {
		font-size: 18px;
	}
}

.input-group {
	margin-top: 210px;
	margin-left: 350px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" />
</head>
<body>

	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;
		</a> <a href="InsertBorrower.jsp"><button type="button"
				class="btn btn-info">Insert Borrower</button> </a> <a href="CheckIn.jsp"><button
				type="button" class="btn btn-info">Check-In</button></a> <a><form
				action="CalculateFines">
				<button type="submit" class="btn btn-info">Update Fine</button>
			</form> </a> <a href="FinePayment.jsp"><button type="button"
				class="btn btn-info">Pay Fine</button> </a>
		<a><form action="UserFines">
			<button type="submit" class="btn btn-info">Display Fine</button>
		</form></a>
	</div>

	<span style="font-size: 30px; cursor: pointer" onclick="openNav()">&#9776;</span>

	<script>
		function openNav() {
			document.getElementById("mySidenav").style.display = "block";
		}

		function closeNav() {
			document.getElementById("mySidenav").style.display = "none";
		}
	</script>

	<form action="BookSearch">

		<div class="input-group">
			<span>Search:</span>
			<div class="col-lg-5">
				<input type="text" class="form-control" name="search" />
			</div>
		</div>
	</form>
	<%
		Iterator itr;
	%>
	<%
		ArrayList rows = new ArrayList();
		if (request.getAttribute("data") != null) {
			rows = (ArrayList) request.getAttribute("data");
	%>
	<form action="CheckOut">
		<table style="width: 100%">
			<tr>
				<td width="168"><b>ISBN</b></td>
				<td width="168"><b>TITLE</b></td>
				<td width="168"><b>NAME</b></td>
				<td width="168"><b>AVAILABLE</b></td>
			</tr>
			<%
				for (itr = rows.iterator(); itr.hasNext();) {
			%>
			<tr>
				<%
					String st = (String) itr.next();
				%>
				<td width="168"><%=st%></td>
				<td width="168"><%=itr.next()%></td>
				<td width="168"><%=itr.next()%></td>
				<%
					int i = Integer.valueOf((String) itr.next());
							if (i == 1) {
				%>

				<td width="168"><input type="radio" name="checkbtn"
					value="<%=st%>"></td>
				<%
					} else {
				%>
				<td width="168">Checked Out</td>
				<%
					}
				%>

				<%
					}
				%>
			</tr>

			<tr>
				<td>Enter Card ID <input type="text" name="cardid"> <input
					type="Submit" value="Check Out">
			</tr>
			<%
				}
			%>

		</table>
	</form>

</body>
</html>