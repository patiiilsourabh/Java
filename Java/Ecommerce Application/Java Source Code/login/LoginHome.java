package login;

import java.sql.SQLException;
import java.util.Scanner;
import buyproduct.BuyProduct;
import buyproduct.CartOperations;
import operations.CustomerInfoToDisplay;
import operations.DeleteProduct;
import operations.InsertProduct;
import operations.ProductIdToDisplay;
import operations.PurchaseHistoryToDisplay;
import operations.UpdateProduct;

public class LoginHome {

	Scanner scanner = new Scanner(System.in);
//-----------------------------------------------------------------------

	public int LoginSuccess;

	public static String Email;
	public static String Name;
	public static int No;
//---------------------------------------------------------------------

	void home() throws SQLException {

		logininput();

//		if (LoginSuccess == 100) {
//			
//			ProductIdToDisplay productIdToDisplay = new ProductIdToDisplay();
//			for (int i = 0; i < 1; i++) {
//				productIdToDisplay.DispalyProducts();
//			}
//			customerOperations();
//			
//			
//		} else if (LoginSuccess == 200) {
//			adminOperations();
//		} else {
//			home();
//		}
//		home();
	}

//--------------------------------------------------------------------------------------
	public void customerOperations() throws SQLException {
		
		System.out.println("------------------ ✒ What to do ❔ ------------------");
		
		System.out.println("1. Display Products 🔍 \n2. Buy Product 🎁 \n3. Go to Cart 📜 \n4. ChangePassword 🔄 \n5. Logout 🛑");
		int selectoperation = scanner.nextInt();
		
		switch (selectoperation) {
		case 1:
			ProductIdToDisplay productIdToDisplay = new ProductIdToDisplay();
			productIdToDisplay.DispalyAll();
			break;
		case 2:
			BuyProduct buyProduct = new BuyProduct();
			buyProduct.buyAll();
			break;
		case 3:
			CartOperations cartOperations = new CartOperations();
			cartOperations.cartAll();
			break;
		case 4 :
			ChangeCustomerPassword changeCustomerPassword = new  ChangeCustomerPassword();
			changeCustomerPassword.changeCPassword();
			break;
		default:		
			System.out.println("Logged out / Invalid input 🔄 : " + selectoperation);
			LoginHome loginHome = new LoginHome();
			loginHome.logininput();
			home();
		}
		
		customerOperations();

	}

	public void adminOperations() throws SQLException {

		System.out.println("\n\n❔❔❔ What to do ❔❔❔");
		System.out.println("\n1.View Products 2. Display Customer Info  3. Purchase History of customers 4. Insert Product 5. Delete Products 6. Update Products 7. Logout");
		int selectoperation = scanner.nextInt();

		switch (selectoperation) {
		case 1:
			ProductIdToDisplay productIdToDisplay = new ProductIdToDisplay();
			productIdToDisplay.DispalyAll();
			break;
		case 2:
			CustomerInfoToDisplay customerInfoToDisplay = new CustomerInfoToDisplay();
			customerInfoToDisplay.DisplayCustomer();
			break;
		case 3:
			PurchaseHistoryToDisplay purchaseHistoryToDisplay = new PurchaseHistoryToDisplay();
			purchaseHistoryToDisplay.DisplayHistory();
			break;
		case 4:
			InsertProduct insertProduct = new InsertProduct();
			insertProduct.insert();
			break;
		case 5:
			DeleteProduct deleteProduct = new DeleteProduct();
			deleteProduct.delete();
			break;
		case 6:
			UpdateProduct updateProduct = new UpdateProduct();
			updateProduct.update();
			break;
		
		default:
			System.out.println("Logged out / Invalid input 🔄 : " + selectoperation);
			home();
		}
		adminOperations();
	}

// --------------------------------------------------------------------

	public void logininput() throws SQLException {
		System.out.println("\n------------------------------- ❤💕 Welcome ❤💕  -------------------------------\n");
		System.out.println("\n                                 🏠 Home Page 🏠                                  \n");
		System.out.println(
				"\n1. Signup (New Register) \n2. LoginIn (already registered customer) \n3. Login as Admin");

		int loginAs = scanner.nextInt();

		switch (loginAs) {
		case 1: {
			SignUp signUp = new SignUp();
			signUp.sign_Up();
			break;
		}
		case 2: {
			customerlogin();
			break;
		}
		case 3: {
			adminlogin();
			break;
		}
		default:
			System.out.println("Invalid input : "+loginAs);
		}
		logininput();
	}

//--------------------------------------------------------------------

	void customerlogin() throws SQLException {
		LoginCustomerVerify loginCustomerVerify = new LoginCustomerVerify();
		loginCustomerVerify.loginverify();

		this.LoginSuccess = 0;

		if (loginCustomerVerify.varified == 100) {
			System.out.println(
					"-------------------------------------------------------------------------------------------");
			System.out.println("Login Successful as customer 😀");

			StoredCustomerData storedCustomerData = loginCustomerVerify.custumerdetails();
			
			Email = storedCustomerData.StoredEmail;
			Name = storedCustomerData.StoredName;
			No = storedCustomerData.StoredNo;

			System.out.println("Logged In as 😎 : \nCustomer Id : " + No + "\nName : " + Name + "\nEmail : "+ Email);

			this.LoginSuccess = 100;
			
			ProductIdToDisplay productIdToDisplay = new ProductIdToDisplay();
			for (int i = 0; i < 1; i++) {
				productIdToDisplay.DispalyProducts();
			}
			customerOperations();

		} else {
			System.out.println("Invalid input try again 🔄");

			LoginSuccess = 0;

			logininput();
		}
	}

//--------------------------------------------------------------------

	void adminlogin() throws SQLException {
		System.out.println("Enter id and password : ");
		String AdminEmail = scanner.next();
		String Adminpass = scanner.next();

		AdminIdPassword adminIdPassword = new AdminIdPassword();

		int Id = AdminEmail.compareTo(adminIdPassword.getAdminId());
		int Pass = Adminpass.compareTo(adminIdPassword.getAdminPassword());

		this.LoginSuccess = 0;

		if (Id + Pass == 0) {
			System.out.println("-------------------------");
			System.out.println("Admin login successful ✔");

			Email = AdminEmail;
			Name = "Admin";
			No = 0;

			System.out.println("Logged In as 😉: \nID : " + No + ", \nName : " + Name + ", \nEmail : " + Email + ".");

			this.LoginSuccess = 200;
			
			adminOperations();

		} else {
			System.out.println("Invalid Login creadentials");

			this.LoginSuccess = 0;

			logininput();
		}

	}
//----------------------- main method----------------------------------

	public static void main(String[] args) throws SQLException {
		LoginHome loginHome = new LoginHome();
		loginHome.home();

	}

}
