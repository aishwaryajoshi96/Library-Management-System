import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentOfFine extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String card_id= request.getParameter("fines");
		card_id=card_id.trim();
		ArrayList<String> dataList= new ArrayList<String>(); 		
		RequestDispatcher dispatcher = null;
		RequestDispatcher DispatcherObj=null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			String query = "select b.Loan_id,b.Date_in,f.Fine_amt,f.Paid from book_loans as b "
					+ "inner join fines as f on b.Loan_id=f.Loan_id where Card_id=?";
	        PreparedStatement st = conn.prepareStatement(query);
	        st.setString(1, card_id);
	        System.out.println(card_id);
	        ResultSet rs = st.executeQuery();
			if(rs.next()!=false) {
				   dataList.add(rs.getString("Loan_id"));
		           dataList.add(rs.getString("Date_in"));
		           dataList.add(rs.getString("Fine_amt"));
		           dataList.add(rs.getString("Paid"));
			while(rs.next()!=false) {
				   dataList.add(rs.getString("Loan_id"));
		           dataList.add(rs.getString("Date_in"));
		           dataList.add(rs.getString("Fine_amt"));
		           dataList.add(rs.getString("Paid"));
			}
			request.setAttribute("data",dataList);
			dispatcher = request.getRequestDispatcher("/FinePayment.jsp");
				}
				else {
			request.setAttribute("data",null);
			DispatcherObj =request.getRequestDispatcher("/NotFound.jsp");
				}
			rs.close();
			conn.close();
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
