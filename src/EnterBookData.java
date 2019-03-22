
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EnterBookData {

	public static void main(String args[]) throws IOException {
		File trainingFile = new File("books.csv");// reading the file
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(trainingFile));
		String nextLine = null;

		while ((nextLine = reader.readLine()) != null) {
			String[] split = nextLine.split("\\t");// to store the first line i.e names of the attributes

			try {
				// Register JDBC driver
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
				Statement stmt = conn.createStatement();
				stmt.execute("USE Library;");
				PreparedStatement ps = conn.prepareStatement("insert into book(Isbn,title) values(?,?)");
				ps.setString(1, split[0]);
				ps.setString(2, split[2]);
				ps.executeUpdate();

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Data inserted in book table");
	}
}
