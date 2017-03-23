package file_processing;

import adts.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MakeProductArray {
	private static JsonReader jsonReader;
	private static Product[] productArray;
	
	public static Product[] make(JSONArray jsonArray) {
		productArray = new Product[jsonArray.size()];
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject product = (JSONObject) jsonArray.get(i);
			String title = (String) product.get("title");
			String asin = (String) product.get("asin");
			double price = (double) product.get("price");
			String upc = (String) product.get("ean");
			String ean = (String) product.get("upc");
			
			if (upc != null)
				productArray[i] = new Product(asin, title, price, upc);

			else			
				productArray[i] = new Product(asin, title, price, ean);
		}
		return productArray;
	}
}
