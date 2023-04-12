package buyproduct;

import java.sql.SQLException;

public class CartOperations extends Cart {

	public void cartAll() throws SQLException {
		System.out.println("What to do ❔ \n1. View cart 2. Add item to cart, 3. Buy form cart 4. Remove item from cart 5. Exit");

		int cart = scanner.nextInt();

		switch (cart) {
		case 1: {
			storeCartItems();
			displayCart();
			break;
		}
		case 2: {
			addItemOrNot();
			break;
		}
		case 3: {
			cartBuy();
			break;
		}
		case 4: {
			cartRemove();
			break;
		}
		default:
			System.out.println("Exit / Invalid Input 🔄: " + cart);
		}
	}

// Section 1 : Add item to cart ------------------------------------------------------------------------------------------------------
	void addItemOrNot() throws SQLException {

		System.out.println("Add to cart 1.Yes ✔ / 2. Stop Adding to cart ❌");

		int Addornot = scanner.nextInt();

		if (Addornot == 1) {
			storeCartItems();
			addItemAndDisplay();
			updateCartDatabase();

			addItemOrNot();
		} else {
			System.out.println("Stopped Adding to cart");
		}
	}

// Section 2 : Buy from cart ------------------------------------------------------------------------------------------------

	public void cartBuy() throws SQLException {

		getCartDatabase();

		// check quantity:
		for (int i = 0; i < 10; i++) {

			// if mismatch id & qnt then make both 0
			if (this.AllId[i] == 0) {
				this.AllQnt[i] = 0;
			}
			// if qnt = 0 then id = 0
			if (this.AllQnt[i] == 0) {
				this.AllId[i] = 0;
			}

			super.Product_Id_toBuy = this.AllId[i];
			super.Product_Quantity_toBuy = this.AllQnt[i];

			checkProductqnt();

			if (this.AllId[i] != 0 && this.AllQnt[i] != 0) {

				if (super.Product_Quantity_Database >= super.Product_Quantity_toBuy) {
					CheckQuantity = 0;
					System.out.println("Product Id : " + AllId[i] + " quantity available ✔");
				} else {
					CheckQuantity = 1;
					System.out.println("Product Id : " + AllId[i] + "quantity not available ❌ try again 🔄");
				}
			}
		}

		super.Product_Id_toBuy = 0;
		super.Product_Quantity_toBuy = 0;
		super.Product_Quantity_Database = 0;

		// buy product diaplay
		if (CheckQuantity == 0) {

			System.out.println("------------------- 📜 Summary of your shopping  📜 -------------------");

			for (int i = 0; i < 10; i++) {
				super.Product_Id_toBuy = this.AllId[i];
				super.Product_Quantity_toBuy = this.AllQnt[i];

				if (this.AllId[i] != 0 && this.AllQnt[i] != 0) {

					System.out.println("Item no : " + (i + 1) + " Product Id to buy : " + Product_Id_toBuy
							+ " Product Quantity to buy : " + Product_Quantity_toBuy);
				}

				buyProductDisplay();

			}

			// buy payment
			buyPayment();
			if (super.PaymentSuccess==100) {
				
			super.Product_Id_toBuy = 0;
			super.Product_Quantity_toBuy = 0;
			super.Product_Quantity_Database = 0;

			// update quantity
			for (int i = 0; i < 10; i++) {

				super.Product_Id_toBuy = this.AllId[i];
				super.Product_Quantity_toBuy = this.AllQnt[i];
				buyProductUpdateQuantity();
			}

			// update history
			for (int i = 0; i < 10; i++) {
				super.Product_Id_toBuy = this.AllId[i];
				super.Product_Quantity_toBuy = this.AllQnt[i];

				if (this.AllId[i] != 0 && this.AllQnt[i] != 0) {
					updateHistoryCard();
				}
			}
			
			// Empty cart
			for(int i=0;i<10;i++) {
				super.AllId[i]=0;
				super.AllQnt[i]=0;
			}
			updateCartDatabase();
			}else {
				System.out.println("Payment failed");
			}

		}
	}

	// Remove from cart --------------------------------------------------------------------------------------------------
	void cartRemove() throws SQLException {
		storeCartItems();
		
		removeItemAndDisplay();
		
		
	}
}
