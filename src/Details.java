

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Details extends HttpServlet {



/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String search = request.getParameter("borrower");
	search=search.trim();
	ArrayList<String> dataList=new ArrayList<String>();
	response.setContentType("text/html");
	RequestDispatcher dispatcher = null;
	RequestDispatcher DispatcherObj=null;
	try {
		// Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
		Statement stmt = conn.createStatement();
		stmt.execute("USE Library;");
		ResultSet rs = stmt.executeQuery("select bo.fname,b.isbn,b.Card_id,b.Date_in" + 
				" from book_loans as b inner join borrower as bo on b.Card_id=bo.card_id"
				+ " where b.Card_id LIKE '"+ search +"%" +"' OR b.isbn LIKE '"+ search +"%" +"' OR bo.fname LIKE '"+ search +"%" +"'");
		
		if(rs.next()!=false) {
			   dataList.add(rs.getString("fname"));
	           dataList.add(rs.getString("isbn"));
	           dataList.add(rs.getString("Card_id"));
	           dataList.add(rs.getString("Date_in"));
	           //dataList.add(dateFormat.format(calendar.getTime()));
		while (rs.next()){

			           dataList.add(rs.getString("fname"));
			           dataList.add(rs.getString("isbn"));
			           dataList.add(rs.getString("Card_id"));
			           dataList.add(rs.getString("Date_in"));
			        //   dataList.add(dateFormat.format(calendar.getTime()));

			   }
		request.setAttribute("data",dataList);
		dispatcher = request.getRequestDispatcher("/CheckIn.jsp");
			}
			else {
				request.setAttribute("data",null);
		DispatcherObj =request.getRequestDispatcher("/NotFound.jsp");
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  if (dispatcher != null){
	  dispatcher.forward(request, response);
	  } 
	  else if(DispatcherObj != null){
		  DispatcherObj.forward(request, response);
	  }
}
}
