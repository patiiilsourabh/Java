package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginCustomerVerify {

	Scanner scanner = new Scanner(System.in);

	String CustomerEmail;
	String CustomerPassword;

	String DatabaseEmail;
	String DatabasePassword;
	String DatabaseName;
	int DatabaseNo;

	int varified;

//------------------------------------------------------------------------------------------------

	void getdata() {
		
		System.out.println("Enter Customer Email");
		
		this.CustomerEmail = scanner.next();
			
		System.out.println("Enter Customer Password");

		this.CustomerPassword = scanner.next();
		
		
		
	}

//------------------------------------------------------------------------------------------------

	public StoredCustomerData custumerdetails() {
		String Email = this.DatabaseEmail;
		String Name = this.DatabaseName;
		int No = this.DatabaseNo;

		return new StoredCustomerData(Email, Name, No);
	}
//-----------------------------------------------------------------------------------------------------

	void loginverify() throws SQLException {

		this.CustomerEmail = null;
		this.CustomerPassword = null;
		
		getdata();

		this.varified = 0;

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from Customer_Information");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println("--------------------------------------");

			while (rs.next()) {
				this.DatabaseEmail = rs.getString("CustomerEmail");
				this.DatabasePassword = rs.getString("CustomerPassword");
				this.DatabaseName = rs.getString("CustomerName");
				this.DatabaseNo = rs.getInt("CustomerNo");

				int em = this.DatabaseEmail.compareTo(this.CustomerEmail);
				int pw = this.DatabasePassword.compareTo(this.CustomerPassword);

				if (em + pw == 0) {
					this.varified = 100;
					break;
				} else {
					this.varified = 0;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
}
