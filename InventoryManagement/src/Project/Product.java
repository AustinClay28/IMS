package Project;

public class Product {
	private String prodName;
	private String brandName;
	private float price;
	private float salesTax;
	private float finalPrice;
	
	public Product(String prodName, String brandName, float price) {
		super();
		this.prodName = prodName;
		this.brandName = brandName;
		this.price = price;
		this.salesTax = price * .07f;
		this.finalPrice = price + salesTax;
	}

	public String getProdName() {
		return prodName;
	}

	public String getBrandName() {
		return brandName;
	}

	public float getPrice() {
		return price;
	}

	public float getSalesTax() {
		return salesTax;
	}

	public float getFinalPrice() {
		return finalPrice;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setSalesTax(float salesTax) {
		this.salesTax = salesTax;
	}

	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	@Override
	public String toString() {
		String formattedTax = String.format("%.2f", salesTax);
		String formattedFinalPrice = String.format("%.2f", finalPrice);
		
		return "PRODUCT: " + prodName + " | BRAND: " + brandName + " | PRICE: " + price + " | TAX: "
				+ formattedTax + " | FINAL: " + formattedFinalPrice;
	}
}
