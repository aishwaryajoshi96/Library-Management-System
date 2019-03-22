
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EnterBookAuthor {

	public static void main(String args[]) throws IOException {
		File trainingFile = new File("books.csv");// reading the file
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(trainingFile));
		String nextLine = null;
		//int line=0;

		while ((nextLine = reader.readLine()) != null) {
			String[] split = nextLine.split("\\t");// to store the first line i.e names of the attributes
				try {
					// Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
					Statement stmt = conn.createStatement();
					stmt.execute("USE Library;");
					//System.out.println(split[3]);
			        String query = "Select author_id from author where author_name = ?";
			        PreparedStatement st = conn.prepareStatement(query);
			        st.setString(1, split[3]);
			        ResultSet rs = st.executeQuery();
			        
					//ResultSet rs = stmt.executeQuery("select author_id from author where author_name="+split[3]+"");
					
					if(rs.next()!=false) {
					PreparedStatement ps = conn.prepareStatement("insert into book_author(author_id,Isbn) values(?,?)");
					ps.setString(1,rs.getString("author_id"));
					ps.setString(2,split[0]);
					ps.executeUpdate();
					}
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			//line++;
			
		}
		System.out.println("Data inserted in author table");
	}
}
