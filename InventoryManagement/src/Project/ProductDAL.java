package Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class ProductDAL {
	
	static Logger logger = Logger.getLogger(Product.class.getName());
		
	private ArrayList<Product> products;
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement prepStatement = null;
	private ResultSet resultSet = null;
	
	//Establish Database Connection within constructor.
	public ProductDAL() {
		products = new ArrayList<>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mystore?characterEncoding=utf8","sqluser","password");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Adds product to database.
	public void add(Product p) {
		if (p != null) {
			try {
			statement.executeUpdate("INSERT INTO products (name, brand, price, salestax, finalprice)" 
					+ "VALUES (\'" + p.getProdName() + "\', '" + p.getBrandName() + "\', " + p.getPrice() + ", " + p.getSalesTax() +", " + p.getFinalPrice() + ")");
			logger.log(Level.INFO, "ADDED: " + p.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void delete(int ID) {
		try{
			statement.executeUpdate("DELETE FROM products WHERE productID = " + ID);
			logger.log(Level.INFO, "DELETED item at ID " + ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateItem(int ID, Product p) {
		try {
			statement.executeUpdate("UPDATE products\r\n"
					+ "SET Name = \'" + p.getProdName() + "\', Brand = \'" + p.getBrandName() + "\', Price = "
					+ p.getPrice() + ", SalesTax = " + p.getSalesTax() + ", FinalPrice = " + p.getFinalPrice() + "\r\n"
					+ "WHERE productID = " + ID);
			logger.log(Level.INFO, "UPDATED item at ID " + ID);	
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
	}

	public void printer(String query) {
		try {
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				String[] productString = new String[7];
				for(int i=0; i < 6; i++) {
					productString[i] = resultSet.getString(i + 1) + " ";
				}
				System.out.println("ID: " + productString[0] + "Name: " + productString[1] + "Brand: " + productString[2] + "Price: " + productString[3] + "SalesTax: " +
				productString[4] + "FinalPrice " + productString[5]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
