
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinePaid extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String checked= request.getParameter("checkbtn");
		String datein=null;
		PrintWriter out = response.getWriter();

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("select Date_in from book_loans where Loan_id="+checked+" ");
			
			if(rs.next()!=false) {
				   datein=rs.getString("Date_in");
				   
				   if(datein!=null) {
					   
				
					Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
					Statement stmt1 = conn1.createStatement();
					stmt1.execute("USE Library;");
					ResultSet rs1 = stmt1.executeQuery("select Paid from fines where Loan_id="+checked+" ");

					if(rs1.next()!=false) {
					int paid=Integer.parseInt(rs1.getString("Paid"));
					
					if(paid==0) {
						Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
						Statement stmt2 = conn1.createStatement();
						stmt2.execute("USE Library;");
						PreparedStatement ps3 = conn2
								.prepareStatement("update fines set Paid='"+1+"' where Loan_id='"+checked+"'");
						ps3.executeUpdate();
					}
					else {
						   out.println("Already paid");

					}		
					}
				}
				   else {  
					   out.println("Not yet returned");
				   }
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		}

}
