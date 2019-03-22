

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertBorrower extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 273142783667193929L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ssn = request.getParameter("Ssn");
		String fname = request.getParameter("First_Name");
		String lname = request.getParameter("Last_Name");
		String address = request.getParameter("Address");
		String phone = request.getParameter("Phone");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			ResultSet rs = stmt.executeQuery("SELECT * from borrower WHERE ssn='" + ssn + "'"); //check whether ssn exists
			if (rs.next() == false) {	//if ssn does not exist then new borrower
				PreparedStatement ps = conn
						.prepareStatement("insert into borrower(ssn,fname,lname,address,phone) values(?,?,?,?,?)");
				ps.setString(1, ssn);
				ps.setString(2, fname);
				ps.setString(3, lname);
				ps.setString(4, address);
				ps.setString(5, phone);
				ps.executeUpdate();
				
				Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
				Statement stmt1 = conn1.createStatement();
				stmt1.execute("USE Library;");
				String query = "select card_id from borrower where ssn= ?";
		        PreparedStatement st = conn.prepareStatement(query);
		        st.setString(1, ssn);
		        ResultSet rs1 = st.executeQuery();

		        if(rs1.next()!=false) {
				//ResultSet rs1 = stmt1.executeQuery("select card_id from borrower where ssn="+ssn +""); //assigning
				//card_id to the new borrower
				System.out.println(rs1.getString("card_id"));
				out.println("Borrower inserted succesfully and the Card ID is:" + rs1.getString("card_id"));
		        }
			}
			
			else {
				out.println("Borrower already exists with same SSN");
			}

			conn.close();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

	}
}
