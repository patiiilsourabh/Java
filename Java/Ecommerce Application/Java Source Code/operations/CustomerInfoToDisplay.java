package operations;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerInfoToDisplay {

	Scanner scanner = new Scanner(System.in);

	int CustomerNo;

	void input() {
		System.out.println("Enter Customer Number (Enter 0 to display all data) : ");
		this.CustomerNo = scanner.nextInt();
	}

// ---------------------------------------------------------------------------------------------

	public void DisplayCustomer() throws SQLException {

		input();

		if (this.CustomerNo == 0) {

			Connection con = null;
			PreparedStatement ps = null;
			try {
				// Step-1- Loading the driver class
				Class.forName("com.mysql.jdbc.Driver");
				// Step-2- Establish the connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root","root");
				// Step-3
				ps = con.prepareStatement("select * from customer_information");
				// Step-4
				ResultSet rs = ps.executeQuery();

				System.out.println("--------------------------------------");
				while (rs.next()) {
					System.out.println("Customer Number : " + rs.getInt("CustomerNo"));
					System.out.println("Customer Email : " + rs.getString("CustomerEmail"));
					System.out.println("Customer Name : " + rs.getString("CustomerName"));
					System.out.println("Customer Address : " + rs.getString("CustomerAddress"));
					System.out.println("Customer Mobile : " + rs.getInt("CustomerMobile"));

					System.out.println("--------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				ps.close();
			}
		}else {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				// Step-1- Loading the driver class
				Class.forName("com.mysql.jdbc.Driver");
				// Step-2- Establish the connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root","root");
				// Step-3
				ps = con.prepareStatement("select * from customer_information where CustomerNo=?");
				ps.setInt(1, this.CustomerNo);
				// Step-4
				ResultSet rs = ps.executeQuery();

				System.out.println("--------------------------------------");
				while (rs.next()) {
					System.out.println("Customer Number : " + rs.getInt("CustomerNo"));
					System.out.println("Customer Email : " + rs.getString("CustomerEmail"));
					System.out.println("Customer Name : " + rs.getString("CustomerName"));
					System.out.println("Customer Address : " + rs.getString("CustomerAddress"));
					System.out.println("Customer Mobile : " + rs.getInt("CustomerMobile"));

					System.out.println("--------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				ps.close();
			}
		}
	}

/*
	public static void main(String[] args) throws SQLException {
		CustomerInfoToDisplay customerInfoToDisplay = new CustomerInfoToDisplay();
		customerInfoToDisplay.DisplayCustomer();
	} 
*/
}
