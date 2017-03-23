package adts;

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
	 * Compares two products based on asin first and price second
	 * @return 1 if this product is has a lower asin than the other product
	 * 		   1 if this product has the same asin as the other product and a lower price
	 * 		   -1 if other product has a lower asin than this product
	 * 		   -1 if other product has a the same asin as the other product and a lower price
	 * 		   0 if both products have the same asin and price
	 */
	public int compareTo(Product other) {
		if (this.asin < other.asin)
			return 1;
		else if	 (this.asin > other.asin)
			return -1;
		else if (this.price < other.price)
			return 1;
		else if (this.price > other.price)
			return -1;
		else 
			return 0;	
	}
}
