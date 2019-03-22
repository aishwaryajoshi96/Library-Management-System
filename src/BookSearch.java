import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;

public class BookSearch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String search = request.getParameter("search");
		search=search.trim();
		
		ArrayList<String> dataList= new ArrayList<String>(); 		
		
		response.setContentType("text/html");
		
		
		RequestDispatcher dispatcher = null;
		RequestDispatcher DispatcherObj=null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			
			//query select ISBN title Author ....radio button(available)
			ResultSet rs = stmt.executeQuery("select c.Isbn, c.Title, c.available, a.author_name" + 
					" from (author as a inner join book_author as b on a.author_id=b.author_id)"
					+ " inner join book as c on c.Isbn=b.Isbn"
					+ " where a.author_name LIKE '"+ search +"%" +"' OR c.Isbn LIKE '"+ search +"%" +"' OR c.Title LIKE '"+ search +"%" +"'");
				if(rs.next()!=false) {
			while (rs.next()){
				           dataList.add(rs.getString("Isbn"));
				           dataList.add(rs.getString("Title"));
				           dataList.add(rs.getString("author_name"));
				           dataList.add(rs.getString("available"));
				   }
			request.setAttribute("data",dataList);
			dispatcher = request.getRequestDispatcher("/MenuPage.jsp");

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
