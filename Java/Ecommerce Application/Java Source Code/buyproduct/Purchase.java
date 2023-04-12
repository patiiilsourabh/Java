package buyproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import login.LoginHome;

public class Purchase {

	Scanner scanner = new Scanner(System.in);

//----------------------------------------------------- class fields -------------------------------------------------------
	// Values entered by user
	int Product_Id_toBuy;
	int Product_Quantity_toBuy;

	// Values from database
	int Product_Quantity_Database;
	int Product_Price_Database;
	String Product_Name_Database;

	// Calculated values
	int TotalPrice;
	
	// payment
	String PaymentId;
	int PaymentPin;
	String DatabasePaymentId;
	int DatabasePaymentPin;
	int PaymentSuccess;

	static String Email;
	static String Name;
	static int No;

//--------------------------------------------- store User Info -------------------------------------------------------------------------------------------------

	void storeuserInfo() {
//	LoginHome loginHome = new LoginHome();
	
	Email = LoginHome.Email;
	Name = LoginHome.Name;
	No = LoginHome.No;
	}
	
//-------------------------------------------- buy product display information and store values in fields -------------------------------------------------

	public void buyProductDisplay() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information where ProductId=?");
			ps.setInt(1, this.Product_Id_toBuy);
			// Step-4- Submit SQL statement to database.
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int ProductId = rs.getInt("ProductId");

				String Productname = rs.getString("ProductName");
				int ProductPrice = rs.getInt("ProductPrice");
				int ProductQuantity = rs.getInt("ProductQuantity");

				// update data in this class
				this.Product_Name_Database = Productname;
				this.Product_Price_Database = ProductPrice;
				this.Product_Quantity_Database = ProductQuantity;

				this.TotalPrice = (this.TotalPrice + (ProductPrice * Product_Quantity_toBuy));

				System.out.println("📝 Product Information : ");
				System.out.println("Product Id : " + ProductId);
				System.out.println("Product Name : " + Productname);
				System.out.println("Product Price : " + ProductPrice);
				System.out.println("Product Quantity Available : " + ProductQuantity);
				System.out.println("Product Quntity to buy " + Product_Quantity_toBuy);
				System.out.println("Total Cost of this product : " + (ProductPrice*Product_Quantity_toBuy));

				//System.out.println("\nTotal amount to payable  : " + this.TotalPrice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}

// --------------------------------------- restore database values to fields ------------------------------------------------------------------------

	void restorefields() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information where ProductId=?");
			ps.setInt(1, this.Product_Id_toBuy);
			// Step-4- Submit SQL statement to database.
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// int ProductId = rs.getInt("ProductId");
				String Productname = rs.getString("ProductName");
				int ProductPrice = rs.getInt("ProductPrice");
				int ProductQuantity = rs.getInt("ProductQuantity");

				// update data in this class
				this.Product_Name_Database = Productname;
				this.Product_Price_Database = ProductPrice;
				this.Product_Quantity_Database = ProductQuantity;

				this.TotalPrice += ProductPrice * Product_Quantity_toBuy;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			con.close();
			ps.close();
		}
	}

// --------------------------------------- buy Product Update Quantity --------------------------------------

	void buyProductUpdateQuantity() throws SQLException {

		restorefields();

		int UpdatedProductQuantity = this.Product_Quantity_Database - this.Product_Quantity_toBuy;

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- for multiple value- using prepared statements
			ps = con.prepareStatement("update product_information set ProductQuantity=? where ProductId=?");
			ps.setInt(1, UpdatedProductQuantity);
			ps.setInt(2, this.Product_Id_toBuy);
			// Step-4- Submit SQL statement to database.
			int b = ps.executeUpdate();
			System.out.println("Product Quantity is updated ✔ : " + b);

			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			con.close();
			ps.close();
		}
	}

//------------------------------------------------ Update History Card -----------------------------------------------------------------

	void updateHistoryCard() throws SQLException {
		
		restorefields();

		storeuserInfo();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- Prepare SQL Statements
			ps = con.prepareStatement("insert into Purchase_History(CustomerNo,CustomerEmail,CustomerName,ProductIdPurchased,ProductNamePurchased,QuantityPurchased,ProductPrice)values(?,?,?,?,?,?,?)");

			
			
			ps.setInt(1, No);
			ps.setString(2, Email);
			ps.setString(3, Name);
			ps.setInt(4, this.Product_Id_toBuy);
			ps.setString(5, this.Product_Name_Database);
			ps.setInt(6, this.Product_Quantity_toBuy);
			ps.setInt(7, this.Product_Price_Database);

			// Step-4- Submit SQL statement to database.
			int a = ps.executeUpdate();
			System.out.println("History Card is Updated ✔ :" + a);

			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			con.close();
			ps.close();
		}
	}

// --------------------------------------- Payment ------------------------------------------------------

	void buyPayment() {

		storeuserInfo();
		
		System.out.println("------------------ 💌 Payment 💌 ------------------");
		System.out.println("\n Total amount to pay  : " + this.TotalPrice);
		
		try {
			System.out.println("Enter Payment Mode : \n1. Upi 2. Debit card, 3. Creadit card");
			int PaymentMode = scanner.nextInt();

			System.out.println("Enter Id and Pin");
			this.PaymentId = scanner.next();
			this.PaymentPin = scanner.nextInt();

			Connection con = null;
			PreparedStatement ps = null;
			try {
				// Step-1- Loading the driver class
				Class.forName("com.mysql.jdbc.Driver");
				// Step-2- Establish the connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
				// Step-3
				ps = con.prepareStatement("select * from customer_information where CustomerNo=?");
				ps.setInt(1, No);
				// Step-4- Submit SQL statement to database.
				ResultSet rs = ps.executeQuery();

				
				
				while (rs.next()) {
					
					
					
					this.DatabasePaymentId = rs.getString("PaymentId");
					this.DatabasePaymentPin = rs.getInt("PaymentPassword");
					
					
					int IdVarified=this.PaymentId.compareTo(this.DatabasePaymentId) ;
					boolean PasswordVarified = this.PaymentPin == this.DatabasePaymentPin;
					
					
					if (IdVarified==0 && PasswordVarified==true) {
						this.PaymentSuccess=100;
				}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				ps.close();
			}
						
			
			
			switch (PaymentMode) {
			case 1:
				if (this.PaymentSuccess==100) {
					System.out.println("Payment Successful ✔");
					System.out.println("Your product will be delivered soon... 🚚 🚀 \nContact 1234567890 for any query 📱\n🙏 THANK YOU 🙏");

					System.out.println("---------------------------------------------------------------------");
					break;
				} else {
					System.out.println("Payment Failed ❌");
				}
				break;
			case 2:
				if (this.PaymentSuccess==100) {
					System.out.println("Payment Successful ✔");
					System.out.println("Your product will be delivered soon... 🚚 🚀 \nContact 1234567890 for any query 📱\n🙏 THANK YOU 🙏");

					System.out.println("---------------------------------------------------------------------");
					break;
				} else {
					System.out.println("Payment Failed ❌");
				}
				break;

			case 3:
				if (this.PaymentSuccess==100) {
					System.out.println("Payment Successful ✔");
					System.out.println("Your product will be delivered soon... 🚚 🚀 \nContact 1234567890 for any query 📱\n🙏 THANK YOU 🙏");

					System.out.println("---------------------------------------------------------------------");
					break;
				} else {
					System.out.println("Payment Failed ❌");
				}
				break;
			default:
				System.out.println("Invalid Input " + PaymentMode);
			}

		} catch (Exception e) {
			System.out.println("Faild try again... 🔄");
			buyPayment();
		}
	}

}
