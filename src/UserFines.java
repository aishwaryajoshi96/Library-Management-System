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

public class UserFines extends HttpServlet {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			ArrayList<String> dataList= new ArrayList<String>(); 		

			response.setContentType("text/html");
			try {
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
				Statement stmt = conn.createStatement();
				stmt.execute("USE Library;");
				ResultSet rs = stmt.executeQuery("select b.card_id,bo.fname,sum(Fine_amt)" + 
						" from book_loans as b inner join Fines as f on b.Loan_id=f.Loan_id inner join borrower as bo"
						+ " on bo.card_id=b.card_id"
						+ " where paid="+0+" group by card_id");
				while (rs.next()){
			           dataList.add(rs.getString("card_id"));
			           dataList.add(rs.getString("fname"));
			           dataList.add(rs.getString("sum(Fine_amt)"));
			   }
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("data",dataList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/DisplayFine.jsp");

			  if (dispatcher != null){
			  dispatcher.forward(request, response);
			  } 

	}
}
