package operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteProduct {

	Scanner scanner = new Scanner(System.in);
	
	int ProductId;
	
	void input() {
		System.out.println("Enter ProductId to Delete 🗑 (Note : Once data will deleted then impossible to recycle) : ");
		this.ProductId = scanner.nextInt();
	}
	
	public void delete() throws SQLException {

		input();
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Step-1- Loading the driver class
			Class.forName("com.mysql.jdbc.Driver");
			// Step-2- Establish the connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/z_project_ecommerce?useSSL=false", "root", "root");
			// Step-3- using prepared statements
			ps = con.prepareStatement("delete from product_information where ProductId=?");
			ps.setInt(1,this.ProductId);
			// Step-4-
			int c = ps.executeUpdate();
			System.out.println("Record is deleted 🗑 : " + c);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Step-6- release the resource
			// con.close();
			ps.close();
		}
	}
	
/*
	public static void main(String[] args) throws SQLException {
		DeleteProduct deleteProduct=new DeleteProduct();
		deleteProduct.delete();
	}
*/
}
