package buyproduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cart extends Purchase {

	int[] AllId = new int[10];
	int[] AllQnt = new int[10];

	int CheckQuantity;

//--------------------------------------------- Store Cart Items And Display ---------------------------------------------------
	void storeCartItems() throws SQLException {

		getCartDatabase();

		// if mismatch id & qnt after entering input then make both 0
		for (int i = 0; i < 10; i++) {
			// if id = 0 then qnt = 0
			if (this.AllId[i] == 0) {
				this.AllQnt[i] = 0;
			}
			// if qnt = 0 then id = 0
			if (this.AllQnt[i] == 0) {
				this.AllId[i] = 0;
			}
		}
		
		
	}

//-------------------------------------------- Add item -------------------------------------------------------------------------------
	
	void addItemAndDisplay() {
		System.out.println("Enter product Id ✍");
		int Product_Id_cart = scanner.nextInt();
		System.out.println("Enter product Quantity ✍");
		int Product_Quantity_cart = scanner.nextInt();
		
		// if id = 0 & qnt = 0 then add item at that palce
		int counter=1;
		for (int i = 0; i < 10; i++) {
			if (this.AllId[i] == 0 && this.AllQnt[i] == 0) {
				this.AllId[i] = Product_Id_cart;
				this.AllQnt[i] = Product_Quantity_cart;
				break;
			} else if (this.AllId[i] != 0 && this.AllQnt[i] != 0) {
				counter++;
			}
			if (counter == 10) {
				System.out.println("Unable to add more than 10 items to the cart 🤐 ");
			} 
		}

		// if mismatch id & qnt then make both 0
		for (int i = 0; i < 10; i++) {
			// if id = 0 then qnt = 0
			if (this.AllId[i] == 0) {
				this.AllQnt[i] = 0;
			}
			// if qnt = 0 then id = 0
			if (this.AllQnt[i] == 0) {
				this.AllId[i] = 0;
			}
		}

		// Display cart
		displayCart();
	}

	
	//-------------------------------------------- Add item -------------------------------------------------------------------------------
	
		void removeItemAndDisplay() throws SQLException {
			System.out.println("Enter product Id ✍");
			int Product_Id_cart = scanner.nextInt();
			
			// if id = 0 & qnt = 0 then add item at that palce
			for (int i = 0; i < 10; i++) {
				if (this.AllId[i] == Product_Id_cart) {
					this.AllId[i] = 0;
					this.AllQnt[i] = 0;
				}
			}

			// if mismatch id & qnt then make both 0
			for (int i = 0; i < 10; i++) {
				// if id = 0 then qnt = 0
				if (this.AllId[i] == 0) {
					this.AllQnt[i] = 0;
				}
				// if qnt = 0 then id = 0
				if (this.AllQnt[i] == 0) {
					this.AllId[i] = 0;
				}
			}

			//update database
			updateCartDatabase();
			
			// Display cart
			displayCart();
		}
		
// ------------------------------------------- getCartDatabase -----------------------------------------------------

	void getCartDatabase() throws SQLException {

		storeuserInfo();

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from Customer_Information where CustomerNo=?");
			ps.setInt(1, Purchase.No);
			// Step-4
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				this.AllId[0] = rs.getInt("Cart1Id");
				this.AllQnt[0] = rs.getInt("Cart1Qnt");
				this.AllId[1] = rs.getInt("Cart2Id");
				this.AllQnt[1] = rs.getInt("Cart2Qnt");
				this.AllId[2] = rs.getInt("Cart3Id");
				this.AllQnt[2] = rs.getInt("Cart3Qnt");
				this.AllId[3] = rs.getInt("Cart4Id");
				this.AllQnt[3] = rs.getInt("Cart4Qnt");
				this.AllId[4] = rs.getInt("Cart5Id");
				this.AllQnt[4] = rs.getInt("Cart5Qnt");
				this.AllId[5] = rs.getInt("Cart6Id");
				this.AllQnt[5] = rs.getInt("Cart6Qnt");
				this.AllId[6] = rs.getInt("Cart7Id");
				this.AllQnt[6] = rs.getInt("Cart7Qnt");
				this.AllId[7] = rs.getInt("Cart8Id");
				this.AllQnt[7] = rs.getInt("Cart8Qnt");
				this.AllId[8] = rs.getInt("Cart9Id");
				this.AllQnt[8] = rs.getInt("Cart9Qnt");
				this.AllId[9] = rs.getInt("Cart10Id");
				this.AllQnt[9] = rs.getInt("Cart10Qnt");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			// con.close();
			ps.close();
		}
	}

//------------------------------------------ Display Cart ---------------------------------------------
	void displayCart() {
		System.out.println("📜📜 Products in Your cart 📜📜");
		for (int i = 0; i < 10; i++) {

			if (this.AllId[i] != 0 && this.AllQnt[i] != 0) {
				System.out.println(" Id: " + this.AllId[i] + " Qntatity: " + this.AllQnt[i]);
			}
		}
	}

//------------------------------- updateCartDatabase -------------------------------------------

	void updateCartDatabase() throws SQLException {

		storeuserInfo();

		String Eml = Purchase.Email;

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- for multiple value- using prepared statements
			ps = con.prepareStatement(
					"update Customer_Information set Cart1Id=?,Cart1Qnt=?,Cart2Id=?,Cart2Qnt=?,Cart3Id=?,Cart3Qnt=?,Cart4Id=?,Cart4Qnt=?,Cart5Id=?,Cart5Qnt=?,Cart6Id=?,Cart6Qnt=?,Cart7Id=?,Cart7Qnt=?,Cart8Id=?,Cart8Qnt=?,Cart9Id=?,Cart9Qnt=?,Cart10Id=?,Cart10Qnt=? where CustomerEmail=?");

			ps.setInt(1, this.AllId[0]);
			ps.setInt(2, this.AllQnt[0]);
			ps.setInt(3, this.AllId[1]);
			ps.setInt(4, this.AllQnt[1]);
			ps.setInt(5, this.AllId[2]);
			ps.setInt(6, this.AllQnt[2]);
			ps.setInt(7, this.AllId[3]);
			ps.setInt(8, this.AllQnt[3]);
			ps.setInt(9, this.AllId[4]);
			ps.setInt(10, this.AllQnt[4]);
			ps.setInt(11, this.AllId[5]);
			ps.setInt(12, this.AllQnt[5]);
			ps.setInt(13, this.AllId[6]);
			ps.setInt(14, this.AllQnt[6]);
			ps.setInt(15, this.AllId[7]);
			ps.setInt(16, this.AllQnt[7]);
			ps.setInt(17, this.AllId[8]);
			ps.setInt(18, this.AllQnt[8]);
			ps.setInt(19, this.AllId[9]);
			ps.setInt(20, this.AllQnt[9]);
			ps.setString(21, Eml);

			// Step-4- Submit SQL statement to database.
			int a = ps.executeUpdate();
			System.out.println("Your cart updated : ✔" + a);

			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			// con.close();
			ps.close();
		}
	}

//-------------------------------------------------check product quantity -------------------------------------------

	public void checkProductqnt() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information where ProductId=?");
			ps.setInt(1, super.Product_Id_toBuy);
			// Step-4- Submit SQL statement to database.
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				// int ProductId = rs.getInt("ProductId");
				// String Productname = rs.getString("ProductName");
				int ProductPrice = rs.getInt("ProductPrice");
				int ProductQuantity = rs.getInt("ProductQuantity");
				super.Product_Quantity_Database = ProductQuantity;
				super.TotalPrice += ProductPrice * Product_Quantity_toBuy;

				System.out.println("Product Quantity : " + ProductQuantity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//-----------------------------------------------------------------------------------------------------------------

	/*
	 * public static void main(String[] args) throws SQLException { Cart cart = new
	 * Cart(); cart.cartAll(); }
	 */

}
