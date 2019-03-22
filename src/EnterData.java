
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EnterData {

	public static void main(String args[]) throws IOException {
		File trainingFile = new File("borrowers.csv");// reading the file
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(trainingFile));
		String nextLine = null;
		
		while ((nextLine = reader.readLine()) != null) {
			String[] split = nextLine.split(",");// to store the first line i.e names of the attributes

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root", "root123");
			Statement stmt = conn.createStatement();
			stmt.execute("USE Library;");
			PreparedStatement ps = conn
					.prepareStatement("insert into borrower(ssn,fname,lname,address,phone) values(?,?,?,?,?)");
			ps.setString(1, split[1]);
			ps.setString(2, split[2]);
			ps.setString(3, split[3]);
			ps.setString(4, split[5]);
			ps.setString(5, split[8]);
			
	ps.executeUpdate();
		
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
	}
		}
	System.out.println("Data inserted in borrower table");
}
}
