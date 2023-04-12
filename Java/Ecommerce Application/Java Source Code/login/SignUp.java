package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SignUp {

	Scanner scanner = new Scanner(System.in);

	String CustomerEmail;
	String CustomerPassword;
	String CustomerName;
	String CustomerAddress;
	long CustomerMobile;
	String PaymentId;
	int PaymentPassword;
	
	int NewCustomer;
	String DatabaseEmail;

	// ---------------------------------------------------------------------------------------------

	void getdata() {
		System.out.println("Enter Customer new Password");
		this.CustomerPassword = scanner.next();
		System.out.println("Enter Customer Name");
		this.CustomerName = scanner.next();
		System.out.println("Enter Customer Address");
		this.CustomerAddress = scanner.next();
		System.out.println("Enter Customer Mobile");
		this.CustomerMobile = scanner.nextLong();
		System.out.println("Enter Payment Id");
		this.PaymentId = scanner.next();
		System.out.println("Enter Payment Password");
		this.PaymentPassword = scanner.nextInt();
	}

	// ---------------------------------------------------------------------------------------------

	public void signUpData() throws SQLException {

		getdata();

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- Prepare SQL Statements
			ps = con.prepareStatement("insert into Customer_Information (CustomerEmail , CustomerPassword , CustomerName , CustomerAddress , CustomerMobile , Cart1Id , Cart1Qnt , Cart2Id , Cart2Qnt , Cart3Id , Cart3Qnt , Cart4Id , Cart4Qnt , Cart5Id , Cart5Qnt , Cart6Id , Cart6Qnt , Cart7Id , Cart7Qnt , Cart8Id , Cart8Qnt , Cart9Id , Cart9Qnt , Cart10Id , Cart10Qnt , PaymentId , PaymentPassword)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, this.CustomerEmail);
			ps.setString(2, this.CustomerPassword);
			ps.setString(3, this.CustomerName);
			ps.setString(4, this.CustomerAddress);
			ps.setLong(5, this.CustomerMobile);
					
					ps.setInt(6, 0);
					ps.setInt(7, 0);
					ps.setInt(8, 0);
					ps.setInt(9, 0);
					ps.setInt(10,0);
					ps.setInt(11,0);
					ps.setInt(12,0);
					ps.setInt(13,0);
					ps.setInt(14,0);
					ps.setInt(15,0);
					ps.setInt(16,0);
					ps.setInt(17,0);
					ps.setInt(18,0);
					ps.setInt(19,0);
					ps.setInt(20,0);
					ps.setInt(21,0); 
					ps.setInt(22,0); 
					ps.setInt(23, 0);
					ps.setInt(24, 0);
					ps.setInt(25, 0);
					
			ps.setString(26, this.PaymentId);
			ps.setInt(27, PaymentPassword);

			// Step-4- Submit SQL statement to database.
			int a = ps.executeUpdate();
			System.out.println("Record is added :" + a);
			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			System.out.println("User sign up record is not added : try again");
			//e.printStackTrace();
		} finally {
			// Step-6- release the resource
			con.close();
			ps.close();
		}

	}
// -----------------------------------------------------------------------------------------------------

	int checkData() {
		
		System.out.println("Enter Customer Email");
		this.CustomerEmail = scanner.next();
		
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

			while (rs.next()) {
				//check existing customer
				this.DatabaseEmail =rs.getString("CustomerEmail");
				
				
				int res=this.DatabaseEmail.compareTo(this.CustomerEmail);
				
				if (res==0) {
					this.NewCustomer = 100;	
					break;
				} else {
					this.NewCustomer = 0;
				}
							
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NewCustomer;
		
		
	}
	
// -----------------------------------------------------------------------------------------------------

	void sign_Up() throws SQLException {

		checkData();
//		System.out.println("Enter Customer Email");
//		this.CustomerEmail = scanner.next();
		
		if(this.NewCustomer != 100) {
			System.out.println("✍ Give your details");
			try {
				signUpData();
				System.out.println("Signed in Succedssfully 😀, login again 🔄");
			} catch (SQLException e) {
				System.out.println("Unable to sign up try again 🔄");
				//e.printStackTrace();
			}
		} else {
			System.out.println("✔ Already Registered please login ");
		}
		
	}

	// -----------------------------------------------------------------------------------------------------------
	/*
	 * public static void main(String[] args) throws SQLException { SignUp
	 * signUp=new SignUp(); signUp.signUpData(); }
	 */
}
