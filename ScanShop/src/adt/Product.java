package adt;

public class Product implements Comparable<Product> {
	private int asin;
	private String title;
	private int upc;
	private double price;

	/**
	 * Constructor method for product 
	 * @param asin Amazon standard identification number
	 * @param title The title of the product
	 * @param price The price of the product
	 * @param upc Universal product code, or international article number (EAN)
	 */
	public Product(int asin, String title, int price, int upc) {
		this.asin = asin;
		this.title = title;
		this.upc = upc;
		this.price = price;
		this.upc = upc;
	}
	
	/**
	 * Getter for the asin
	 * @return The asin of the product
	 */
	public int asin() {
		return this.asin;
	}
	
	/**
	 * Getter for the title 
	 * @return The title of the product
	 */
	public String title() {
		return this.title;
	}
	
	/**
	 * Getter for the UPC
	 * @return The UPC code of the product
	 */
	public int upc() {
		return this.upc;
	}
	
	/**
	 * Getter for the price
	 * @return The price of the product
	 */
	public double price() {
		return this.price;
	}

	/**
	 * Compares two products based on price
	 * @return 1 if this product is cheaper than that product
	 * 		   -1 if that product is cheaper this product
	 * 		   0 if the products are priced the same
	 */
	public int compareTo(Product other) {
		if (this.price < other.price())
			return 1;
		else if (this.price > other.price())
			return -1;
		else
			return 0;
	}
}
