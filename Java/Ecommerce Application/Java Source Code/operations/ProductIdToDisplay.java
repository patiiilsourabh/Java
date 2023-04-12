package operations;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductIdToDisplay {

	Scanner scanner = new Scanner(System.in);

	int ProductId;
	int Sort;

// ---------------------------------------------------------------------------------------------

	public void DispalyProducts() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println("---------------------------------------------------------");
			while (rs.next()) {
				System.out.println("Product Id : " + rs.getInt("ProductId"));
				System.out.println("Product Name : " + rs.getString("ProductName"));
				System.out.println("Product Description : " + rs.getString("ProductDescription"));
				System.out.println("Product Price : " + rs.getString("ProductPrice"));
				System.out.println("Product Quantity : " + rs.getString("ProductQuantity"));

				System.out.println("---------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//----------------------------------------------------------------------------------------------
	void inputId() {
		System.out.println("Enter Product Id : ");
		this.ProductId = scanner.nextInt();
	}

	public void DispalyProducts(int Id) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information where ProductId=?");
			ps.setInt(1, this.ProductId);
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println("--------------------------------------");
			while (rs.next()) {
				System.out.println("Product Id : " + rs.getInt("ProductId"));
				System.out.println("Product Name : " + rs.getString("ProductName"));
				System.out.println("Product Description : " + rs.getString("ProductDescription"));
				System.out.println("Product Price : " + rs.getString("ProductPrice"));
				System.out.println("Product Quantity : " + rs.getString("ProductQuantity"));

				System.out.println("--------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//------------------------------------------------------------------------------------------
	public void sortById() throws SQLException {


		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information ORDER by ProductId");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println("Product Id " + "\tProduct Name " + "\t\tProduct Description " + "\t\tProduct Price " + "\tProduct Quantity ");
			System.out.println("------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("ProductId") + "\t\t" + rs.getString("ProductName") + "\t\t\t"
						+ rs.getString("ProductDescription") + "\t\t" + rs.getString("ProductPrice") + "\t\t\t "
						+ rs.getString("ProductQuantity"));
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sortByName() throws SQLException {

		// input();

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information ORDER by ProductName");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println("Product Id " + "\tProduct Name " + "\t\tProduct Description " + "\t\tProduct Price "+ "\tProduct Quantity ");
			System.out.println("------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("ProductId") + "\t\t" + rs.getString("ProductName") + "\t\t\t"+ rs.getString("ProductDescription") + "\t\t" + rs.getString("ProductPrice") + "\t\t\t "+ rs.getString("ProductQuantity"));
				System.out.println("------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sortByPrice() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information ORDER by ProductPrice");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println("Product Id " + "\tProduct Name " + "\t\tProduct Description " + "\t\tProduct Price " + "\tProduct Quantity ");
			System.out.println("------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("ProductId") + "\t\t" + rs.getString("ProductName") + "\t\t\t"
						+ rs.getString("ProductDescription") + "\t\t" + rs.getString("ProductPrice") + "\t\t\t "
						+ rs.getString("ProductQuantity"));
				System.out.println("------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sortByQuantity() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from product_information ORDER by ProductQuantity");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println("------------------------------------------------------------------------------------------------------------------");
			System.out.println("Product Id " + "\tProduct Name " + "\t\tProduct Description " + "\t\tProduct Price " + "\tProduct Quantity ");
			System.out.println("------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("ProductId") + "\t\t" + rs.getString("ProductName") + "\t\t\t"
						+ rs.getString("ProductDescription") + "\t\t" + rs.getString("ProductPrice") + "\t\t\t "
						+ rs.getString("ProductQuantity"));
				System.out.println("------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void SortBy() throws SQLException {
		System.out.println("Sort By 📜 \n1. Id 2. Name 3. Price 4. Quantity");

		this.Sort = scanner.nextInt();

		switch (Sort) {
		case 1: {
			sortById();
			break;
		}
		case 2: {
			sortByName();
			break;
		}
		case 3: {
			sortByPrice();
			break;
		}
		case 4: {
			sortByQuantity();
			break;
		}
		default:
			System.out.println("Invalid Input");
		}

	}

//-------------------------------------------------------------------------------------------
	public void DispalyAll() throws SQLException {
		int AllOrSorted;
		System.out.println(
				"Enter Products to display with \n1. All Products 2. Single Product 3. Sorted list of all Products");
		AllOrSorted = scanner.nextInt();
		switch (AllOrSorted) {

		case 1: {
			DispalyProducts();
			break;
		}
		case 2: {
			inputId();
			DispalyProducts(this.ProductId);
			break;
		}
		case 3: {
			SortBy();
			break;
		}
		default:
			System.out.println("Invalid input : " + AllOrSorted);
		}
	}

//-----------------------------------------------------------------------------------------------
/*
	public static void main(String[] args) throws SQLException {

		ProductIdToDisplay productIdToDisplay = new ProductIdToDisplay();
		productIdToDisplay.DispalyAll();
	}
*/
	
}
