package adts;

public class Product implements Comparable<Product> {
	private String asin;
	private String title;
	private double price;
	private String id; // either upc or ean code

	/**
	 * Constructor method for product 
	 * @param asin Amazon standard identification number
	 * @param title The title of the product
	 * @param price The price of the product
	 * @param upc Universal product code, or international article number (EAN)
	 */
	public Product(String asin, String title, double price, String id) {
		this.asin = asin;
		this.title = title;
		this.id = id;
		this.price = price;
	}
	
	/**
	 * Getter for the asin
	 * @return The asin of the product
	 */
	public String asin() {
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
	 * Getter for the price
	 * @return The price of the product
	 */
	public double price() {
		return this.price;
	}
	
	/**
	 * Getter for the UPC
	 * @return The UPC code of the product
	 */
	public String id() {
		return this.id;
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
		if (asin.compareTo(other.asin()) > 0)
			return 1;
		else if	(asin.compareTo(other.asin()) < 0)
			return -1;
		else if (this.price > other.price)
			return 1;
		else if (this.price < other.price)
			return -1;
		else 
			return 0;	
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return asin + ": (" + title + ") (" + price + ") (" + id +")";
	}
}