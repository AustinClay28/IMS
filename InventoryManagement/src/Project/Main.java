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
		while(true){
			//User prompt
			System.out.println ("Options:\n" + 
							    "1. Display current inventory.\n" +
								"2. Add new Item.\n" +
								"3. Search for specified Item by name.\n" +
								"4. Update specified value.\n" +
								"5. Delete specified Item by ID.\n" +
								"6. Current user information.\n" +
								"7. Exit program.");
			
			int in = Integer.parseInt(sc.nextLine());
			
			//Each option is fairly straightforward and walks you through the process with little need for explanation.
			if(in == 1) {
				pDal.printer("Select * FROM products");
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
				pDal.add(p);
				
			}
			if(in == 3) {
				String name;
				System.out.println("Enter NAME for search.");
				name = sc.nextLine();
				
				pDal.printer(name);
			}
			if(in == 4) {
				int ID;
				String newName;
				String brand;
				float price;
				
				System.out.println("Enter the ID of the product you would like to update:");
				ID = Integer.parseInt(sc.nextLine());
				System.out.println("Enter updated NAME:");
				newName = sc.nextLine();
				System.out.println("Enter updated BRAND:");
				brand = sc.nextLine();
				System.out.println("Enter updated PRICE:");
				price = Float.parseFloat(sc.nextLine());
				
				Product p = new Product(newName, brand, price);
				pDal.updateItem(ID, p);

			}
			if(in == 5) {
				int ID;
				System.out.println("Enter ID of product to be deleted.");
				ID = Integer.parseInt(sc.nextLine());
				pDal.delete(ID);
				
			}
			if(in == 6) {
				System.out.println("USER: " + userName + " GROUPS: " + groups);
			}
			if(in == 7) {
				System.exit(0);
			}
		}
	}
}