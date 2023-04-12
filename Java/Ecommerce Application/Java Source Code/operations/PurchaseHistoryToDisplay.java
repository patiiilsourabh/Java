package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PurchaseHistoryToDisplay {

	
	Scanner scanner = new Scanner(System.in);


// ---------------------------------------------------------------------------------------------

	public void DisplayHistory() throws SQLException {


		System.out.println("Enter Operation to conduct \n1. All History 2. History by customer 3. History by product Name");
		int historyof=scanner.nextInt();
		

		switch (historyof) {
		case 1:
			allHistory();
			break;
		case 2:
			historyByCustomer();
			break;
		case 3:
			historyByProduct();
			break;
		default:
			System.out.println("Invalid input : "+historyof);
		}
		
	}
	
//--------------------------------------------------------------------------------------------------
	
	void allHistory() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root","root");
			// Step-3
			ps = con.prepareStatement("select * from purchase_history");
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println("-------------------------------------------------------------------------------------------");
			while (rs.next()) {
				
				System.out.println("Order Number : " + rs.getInt("SerialNumber"));
				System.out.println("Customer No : " + rs.getInt("CustomerNo"));
				System.out.println("Customer Email : " + rs.getString("CustomerEmail"));
				System.out.println("Customer Name : " + rs.getString("CustomerName"));
				System.out.println("Product Id : " + rs.getInt("ProductIdPurchased"));					
				System.out.println("Product Name : " + rs.getString("ProductNamePurchased"));
				System.out.println("Quantity Purchased : " + rs.getInt("QuantityPurchased"));
				System.out.println("Product Price at time of purchasing : " + rs.getInt("ProductPrice"));

				System.out.println("-------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
	
//-------------------------------------------------------------------------------------------------------
	
	void historyByCustomer() throws SQLException {
		
		System.out.println("Enter Customer No : ");
		int CustomerNo=scanner.nextInt();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from purchase_history where CustomerNo=?");
			ps.setInt(1, CustomerNo);
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Order Number : " +"Customer No " + "\tCustomer Email " + "\tCustomer Name " + "\tProduct Id "
					+ "\tProduct Name" + "\tQuantity Purchased " + "\tProduct Price");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("SerialNumber") +"\t\t"+ rs.getInt("CustomerNo") + "\t\t" + rs.getString("CustomerEmail") + "\t\t\t"+ rs.getString("CustomerName") + "\t\t" + rs.getString("ProductIdPurchased") + "\t\t\t "
+ rs.getString("ProductNamePurchased")+ "\t\t"+rs.getInt("QuantityPurchased") + "\t\t"+rs.getInt("ProductPrice") + "\t\t");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}
	
//-------------------------------------------------------------------------------------------------------
	
	void historyByProduct() throws SQLException {
		System.out.println("Enter Product Id : ");
		int ProductId=scanner.nextInt();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3
			ps = con.prepareStatement("select * from purchase_history where ProductIdPurchased=?");
			ps.setInt(1, ProductId);
			// Step-4
			ResultSet rs = ps.executeQuery();

			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Customer No " + "\tCustomer Email " + "\t\tCustomer Name " + "\t\tProduct Id "
					+ "\tProduct Name" + "\tQuantity Purchased " + "\tProduct Price");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				System.out.println(rs.getInt("CustomerNo") + "\t\t" + rs.getString("CustomerEmail") + "\t\t"
						+ rs.getString("CustomerName") + "\t\t\t" + rs.getString("ProductIdPurchased") + "\t\t "
						+ rs.getString("ProductNamePurchased")+ "\t\t"+rs.getInt("QuantityPurchased") + "\t\t"+rs.getInt("ProductPrice") + "\t\t");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
	}	
	
	
//------------------------------------------------------------------------------------------------------------
	/*
	public static void main(String[] args) throws SQLException {
		PurchaseHistoryToDisplay purchaseHistoryToDisplay = new PurchaseHistoryToDisplay();
		purchaseHistoryToDisplay.DisplayCustomer();
	}
	*/
}
