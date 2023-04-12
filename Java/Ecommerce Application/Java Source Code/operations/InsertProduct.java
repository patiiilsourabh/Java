package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertProduct {

	Scanner scanner = new Scanner(System.in);

	String ProductName;
	String ProductDescription;
	int ProductPrice;
//	int ProductPrce;
	int ProductQuantity;

	// ---------------------------------------------------------------------------------------------
	
	void input() {
		System.out.println("Enter to insert 📝 \nProductName");
		this.ProductName = scanner.next();
		System.out.println("ProductDescription");
		this.ProductDescription = scanner.next();
		System.out.println("Product Price");
		this.ProductPrice = scanner.nextInt();
		System.out.println("Product Quantity");
		this.ProductQuantity = scanner.nextInt();
	}

	// ---------------------------------------------------------------------------------------------
	
	public void insert() throws SQLException {

		input();

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- Prepare SQL Statements
			ps = con.prepareStatement("insert into Product_Information(ProductName,ProductDescription,ProductPrice,ProductQuantity)values(?,?,?,?)");
			
			ps.setString(1, ProductName);
			ps.setString(2, ProductDescription);
			ps.setInt(3, this.ProductPrice);
			ps.setInt(4, this.ProductQuantity);

			// Step-4- Submit SQL statement to database.
			int a = ps.executeUpdate();
			System.out.println("New product is inserted ✔ :" + a);

			// Step-5 Process the results(Internally happen)

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			con.close();
			ps.close();
		}
	}
	
/*
	public static void main(String[] args) throws SQLException {
		InsertProduct insertProduct=new InsertProduct();
		insertProduct.insert();
	}
*/
}
