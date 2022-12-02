package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class ProductDAL {
	
	static Logger logger = Logger.getLogger(Product.class.getName());
		
	private ArrayList<Product> products;
	
	public ProductDAL() {
		products = new ArrayList<>();
	}
	
	public ArrayList<Product> getAll(){
		ArrayList<Product> products = new ArrayList<>();
		
		return products;
	}
	
	public void addProduct(Product p) {
		if (p != null) {
			products.add(p);
			logger.log(Level.INFO, "ADDED: " + p.toString());
		}
	}
	public void search(String name){
		for (Product product : products) {
			if (product.getProdName().contains(name))
			{
				System.out.println(product.toString());
			}
		}
	}
	public void delete(String name) {
		ArrayList<Product> searchResults = new ArrayList<>();
		for (Product product : products) {
			if (product.getProdName().contains(name))
			{
				searchResults.add(product);
				logger.log(Level.INFO, "DELETED: " + product.toString());
			}
		}
		products.removeAll(searchResults);
	}
	public String printAll() {
		String productList = "";
		for (Product product : products) {
			productList += product.toString() + "\n";
		}
		return productList;
	}
	
	public String printSearch(ArrayList<Product> searchResults) {
		String productList = "";
		for (Product product : searchResults) {
			productList += product.toString() + "\n";
		}
		return productList;
	}

	public void updateItem(String name, String newName, String brand, float price) {
		for (Product product : products) {
			if (product.getProdName().contains(name)) {
				product.setProdName(newName);
				product.setBrandName(brand);
				product.setPrice(price);
				product.setSalesTax(price * .07f);
				product.setFinalPrice(price + product.getSalesTax());
				logger.log(Level.INFO, "UPDATED: " + product.toString());
			}
		}
		
	}

	public void instantiateLogger() {
		logger.setLevel(Level.INFO);
		try {
			Handler fileHandler = new FileHandler("C:/Users/dadgi/temp/logger.log", 2000, 5);
			fileHandler.setFormatter(new XMLFormatter());
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException exception) {
			exception.printStackTrace();
		}
	}
}
