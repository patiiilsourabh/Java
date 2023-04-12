package buyproduct;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Scanner;

public class BuyProduct extends Purchase {

//------------------------------------------------------get info-----------------------------------------
	void getinfo() {
		System.out.println("Enter product id to buy ✍ ");
		super.Product_Id_toBuy = scanner.nextInt();
		System.out.println("Enter quantity to buy ✍");
		super.Product_Quantity_toBuy = scanner.nextInt();
	}
	
//-----------------------------------------------------------------------------------------------------------

	public void buyAll() throws SQLException {
		
		getinfo(); //

		buyProductDisplay(); //
		
		
		if (super.Product_Quantity_Database >= super.Product_Quantity_toBuy) {
			
			buyPayment(); //
			if (super.PaymentSuccess==100) {
				buyProductUpdateQuantity(); //
				updateHistoryCard();
			}else {
				System.out.println("Try Again 🔄");
			}
		} else {
			System.out.println("\nquantity not available ❗. \ntry again 🔄\n");
			//buyProductDisplay();
		}
				
	}


//--------------------------------------------------------------------------------------------------------
/*
	 public static void main(String[] args) throws SQLException { BuyProduct
	 buyProduct = new BuyProduct();
	 buyProduct.buyAll();
	 }
*/
}
