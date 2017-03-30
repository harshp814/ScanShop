package file_processing;

import adts.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sorting.Trie;

/**
 * The main class of the backend of our application.
 * Designed to be used as an abstract object.
 * @author Group 1
 */
public class Data {
	
	// Define a JsonReader object for the import file.
	private static JsonReader jsonFile;
	// Define a product ADT array to store our product objects once they are read
	// from the JSON file.
	private static Product[] productArray;
	
	/**
	 * Initialization method for our abstract object. This method
	 * must be called before any other Data methods.
	 * @param filename
	 * @return
	 */
	public static Product[] init(String filename) {
		
		// Initialize a new JsonReader object.
		jsonFile = new JsonReader(filename);
		// Create a JSONArray of the data read in the JSON file.
		JSONArray jsonArray = jsonFile.read();
		// Initialize the product array.
		productArray = new Product[jsonArray.size()];
		// Initialize a product index array (for sorting).
		
		
		// Loop through the JSON array to retrieve the product information.
		for (int i = 0; i < jsonArray.size(); i++) {
			
			// Retrieve a JSON object from the JSON array.
			JSONObject product = (JSONObject) jsonArray.get(i);
			
			// Get the data from the JSON object.
			String title = (String) product.get("title");
			String asin = (String) product.get("asin");
			double price = (Double) product.get("price");
			
			// Get the barcode from the product (can be UPC or EAN).
			long barcode;
			if (product.get("upc") != null) 
				barcode = Long.parseLong(product.get("upc").toString());
			else 
				barcode = Long.parseLong(product.get("ean").toString());
			
			// Create a new Product object with the data.
			productArray[i] = new Product(asin, title, price, barcode);
		
		}
		
		/*
		System.out.println("Build time: " + (System.nanoTime() - now)/1000000000);

		// Uncomment the next line.. IF YOU DARE
		//t.printTree();
		
		String query = "algorithms (4th edition)";
		
		now = System.nanoTime();
		int[] res = t.getBestMatches(query, 10);
		System.out.println("Search time: " + (System.nanoTime() - now)/1000000000);

		System.out.println("\nNumber of Strings: " + t.getNumStrings());
		System.out.println("Number of Nodes: " + t.getNumNodes());
		
		System.out.println("\nResults for query: \"" + query + "\" : ");
		for (int i : res) 
			System.out.printf("Index: %8d \tASIN: %s : %s%n", i,productArray[i].title(),productArray[i].asin());
		*/
		return productArray;
		
	}
}
