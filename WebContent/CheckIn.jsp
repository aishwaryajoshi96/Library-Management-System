<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library System Management</title>
</head>
<body>
<div class="form">
		<form action="Details">
			Search Borrower <input type="text" name="borrower">
		</form>
</div>

<%Iterator itr;%>		
<% 
	ArrayList rows1=new ArrayList();
    if (request.getAttribute("data") != null){
	 rows1=(ArrayList)request.getAttribute("data");
	 
	 
%>

<form action="CheckIn">
<table style="width:100%">
<tr>
<td width="168"><b>NAME</b></td>
<td width="168"><b>ISBN</b></td>
<td width="168"><b>CARD ID</b></td>
</tr>
<%
	 for (itr=rows1.iterator(); itr.hasNext();)
	 {		
 %> 
<tr>
<td width="168"><%=itr.next()%></td>
<%String st=(String)itr.next();
%>
<td width="168"><%=st%></td>
<td width="168"><%=itr.next()%></td>
<%String st1=(String)itr.next();
if(st1==null){%>

<td width="168"><input type="radio" name="checkbtn" value="<%=st%>"></td>
<%}else{%>
	<td width="168">Checked In</td>

<%}%>


<%} %>
</tr>

<tr>
<td><input type="Submit" value="Check In"></td>
</tr>
</form>
</form>
<%}%>

</table> 
      
</body>
</html>