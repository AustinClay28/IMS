package Project;

import java.util.Scanner;

public class Main {
	
	static ProductDAL pDal = new ProductDAL();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String userName = "Admin";
		String groups = "SudoWheel";
		
		
		System.out.println("Welcome to TJ Max, your one stop shop for all you consumer needs.\n");
		pDal.instantiateLogger();
		initializeInventory();
		while(true){
			System.out.println ("Options:\n" + 
							    "1. Display current inventory.\n" +
								"2. Add item to inventory.\n" +
								"3. Delete item from inventory.\n" +
								"4. Update value within inventory.\n" +
								"5. Search inventory by name.\n" +
								"6. Current user information.\n" +
								"7. Exit program.");
			
			int in = Integer.parseInt(sc.nextLine());
			
			if(in == 1) {
				System.out.println("\n" + pDal.printAll());
			}
			if(in == 2) {
				String name, brand;
				float price;
				
				System.out.println("Enter product NAME:");
				name = sc.nextLine();
				System.out.println("Enter product BRAND:");
				brand = sc.nextLine();
				System.out.println("Enter product PRICE:");
				price = Float.parseFloat(sc.nextLine());
				
				Product p = new Product(name, brand, price);
				pDal.addProduct(p);
				
			}
			if(in == 3) {
				String name;
				System.out.println("Enter NAME of product to be deleted.");
				name = sc.nextLine();
				pDal.delete(name);
			}
			if(in == 4) {
				String name;
				String newName;
				String brand;
				float price;
				
				System.out.println("Enter the name of the product you would like to update:");
				name = sc.nextLine();
				System.out.println("Enter updated NAME:");
				newName = sc.nextLine();
				System.out.println("Enter updated BRAND:");
				brand = sc.nextLine();
				System.out.println("Enter updated PRICE:");
				price = Float.parseFloat(sc.nextLine());
				
				pDal.updateItem(name, newName, brand, price);

			}
			if(in == 5) {
				searchPrompt();
				
			}
			if(in == 6) {
				System.out.println("USER: " + userName + " GROUPS: " + groups);
			}
			if(in == 7) {
				System.exit(0);
			}
		}
	}
	public static void generateProduct(String productName, String brandName, float price) {
		Product p = new Product(productName, brandName, price);
		pDal.addProduct(p);
	}
	public static void initializeInventory() {
		generateProduct("Shoes", "Converse", 49.99f);
		generateProduct("Shampoo", "O'riel", 9.99f);
		generateProduct("T-Shirt", "Fruit-O'-The-Loom", 14.99f);
		generateProduct("Canteen", "Klean Kanteen", 29.99f);
		generateProduct("Diapers", "Pampers", 39.99f);
	}
	public static void searchPrompt() {
		String name = "";
		System.out.println("Enter NAME for search.");
		name = sc.nextLine();
		
		pDal.search(name);
	}	
}