package backend.file_processing;

import backend.adts.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.InputStream;

import backend.searching.BinarySearch;
import backend.sorting.Merge;
import backend.sorting.Trie;

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
	 * @param file
	 * @return
	 */
	public static void init(InputStream file) {
		
		// Initialize a new JsonReader object.
		jsonFile = new JsonReader(file);
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
	
	public static Product searchByBarcode(long barcode){
		return BinarySearch.searchID(sortedArray, productArray, barcode);
	}
	
	public static Product[] searchByTitle(String query, int num) {
		
		int[] res = productTitles.getBestMatches(query, num);
		Product[] prods = new Product[num];
		
		for (int ind = 0; ind < num; ind++) 
			prods[ind] = productArray[res[ind]];
		
		return prods;
	}
}
