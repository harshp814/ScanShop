package file_processing;

import adts.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sorting.Trie;

public class MakeProductArray {
	private static JsonReader jsonReader;
	private static Product[] productArray;
	
	public static Product[] make(JSONArray jsonArray) {
		productArray = new Product[jsonArray.size()];
		
		Trie t = new Trie();
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject product = (JSONObject) jsonArray.get(i);
			
			String title = (String) product.get("title");
			t.addWord(title.toLowerCase(), i);
			
			String asin = (String) product.get("asin");
			// TODO fix object -> double conversion for price.
			double price = 0; //Double.parseDouble((String)product.get("price"));
			long upc = 0;
			long ean = 0;
			if (product.get("upc") != null) {
				upc = Long.parseLong(product.get("upc").toString());
				productArray[i] = new Product(asin, title, price, upc);
			}
			else {
				ean = Long.parseLong(product.get("ean").toString());
				productArray[i] = new Product(asin, title, price, ean);
			}
		
		}
		
		t.printData();
		
		return productArray;
	}
}
