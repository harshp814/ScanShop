package file_processing;

import adts.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import searching.BinarySearch;
import sorting.Merge;
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
	// Define an Integer array that will allow us to sort the products by
	// their barcode (ID) without affecting the order of the productArray.
	// This is needed because of how the Trie stores the index of a product title. 
	private static int[] sortedArray;
	// Define a Trie to store all of the titles of the products. This allows us
	// to quickly search strings based on an input string.
	private static Trie productTitles;
	
	/**
	 * Initialization method for our abstract object. This method
	 * must be called before any other Data methods.
	 * @param filename
	 * @return
	 */
	public static void init(String filename) {
		
		// Initialize a new JsonReader object.
		jsonFile = new JsonReader(filename);
		// Create a JSONArray of the data read in the JSON file.
		JSONArray jsonArray = jsonFile.read();
		// Initialize the product array.
		productArray = new Product[jsonArray.size()];
		// Initialize a product index array (for sorting).
		sortedArray = new int[jsonArray.size()];
		// Initialize the Trie data structure to organize titles of products.
		productTitles = new Trie();
		
		// Loop through the JSON array to retrieve the product information.
		for (int i = 0; i < jsonArray.size(); i++) {
			
			// Retrieve a JSON object from the JSON array.
			JSONObject product = (JSONObject) jsonArray.get(i);
			
			// Get the data from the JSON object.
			String title = (String) product.get("title");
			String asin = (String) product.get("asin");
			double price = (Double) product.get("price");
			
			// Add the title to the Trie.
			productTitles.addWord(title, i);
			
			// Get the barcode from the product (can be UPC or EAN).
			long barcode;
			if (product.get("upc") != null) 
				barcode = Long.parseLong(product.get("upc").toString());
			else 
				barcode = Long.parseLong(product.get("ean").toString());
			
			// Create a new Product object with the data.
			productArray[i] = new Product(asin, title, price, barcode);
			// Add the index to the sortedArray array.
			sortedArray[i] = i;
			
		}
		
		// Sort the index array relative to the products in the product Array.
		Merge.sort(sortedArray, productArray);
		
	}
	
	/**
	 * Perform a binary search on our dataset to find a matching barcode.
	 * @param barcode Long number representing the barcode of the product.
	 * @return Product object representing a product found in our dataset.
	 *  Null is returned if no product can be found.
	 */
	public static Product searchByBarcode(long barcode){
		// Return the binary search results.
		return BinarySearch.searchID(sortedArray, productArray, barcode);
	}
	
	/**
	 * Perform a search by product title on our dataset. 
	 * @param query String representing the desired title to search for.
	 * @param num Integer representing the number of best matches to return.
	 * @return Product[] representing the number of closest matches requested
	 * by the user.
	 */
	public static Product[] searchByTitle(String query, int num) {
		
		// Get results from search
		int[] res = productTitles.getBestMatches(query, num);
		// Create Product array.
		Product[] prods = new Product[num];
		
		// Transfer the results into their corresponding products.
		for (int ind = 0; ind < num; ind++) 
			prods[ind] = productArray[res[ind]];
		
		// Return the results.
		return prods;
	}
}
