package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ChangeCustomerPassword {

	Scanner scanner=new Scanner(System.in);
	
	void changeCPassword() throws SQLException {
		
		
		int CustomerNo = LoginHome.No;
		
		System.out.println("\nEnter New Password : ");
		String NewPassword=scanner.next();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- for multiple value- using prepared statements
			ps = con.prepareStatement("update Customer_Information set CustomerPassword=? where CustomerNo=?");
			ps.setString(1, NewPassword);
			ps.setInt(2, CustomerNo);
			
			// Step-4- Submit SQL statement to database.
			int b = ps.executeUpdate();
			System.out.println("Password is updated ✒ : " + b);

			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			// con.close();
			ps.close();
		}
		
	}
	
}
