package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateProduct {

	
	Scanner scanner = new Scanner(System.in);

	String ProductName;
	String ProductDescription;
	int ProductPrice;
	int ProductQuantity;
	int ProductId;

	// ---------------------------------------------------------------------------------------------
	
	void input() {
		System.out.println("Enter to update : \nProductName");
		this.ProductName = scanner.next();
		
		System.out.println("ProductDescription");
		this.ProductDescription = scanner.next();
		
		System.out.println("ProductPrice");
		this.ProductPrice = scanner.nextInt();
		
		System.out.println("ProductQuantity");
		this.ProductQuantity = scanner.nextInt();
		
		System.out.println("ProductId");
		this.ProductId = scanner.nextInt();
		
	}

	// ---------------------------------------------------------------------------------------------
	
	public void update() throws SQLException {

		input();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- for multiple value- using prepared statements
			ps = con.prepareStatement("update product_information set ProductName=?,ProductDescription=?,ProductPrice=?, ProductQuantity=? where ProductId=?");
			ps.setString(1, ProductName);
			ps.setString(2, ProductDescription);
			ps.setInt(3, this.ProductPrice);
			ps.setInt(4, this.ProductQuantity);
			ps.setInt(5, this.ProductId);
			// Step-4- Submit SQL statement to database.
			int b = ps.executeUpdate();
			System.out.println("Product record is updated : " + b);

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
		UpdateProduct updateProduct=new UpdateProduct();
		updateProduct.update();
	}
	*/
}
