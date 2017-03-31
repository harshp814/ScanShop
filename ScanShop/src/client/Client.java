package client;

import adts.Product;
import file_processing.Data;

public class Client {
	
	private final static String JSON_FILE_NAME = "amazon_rawdata.json.gz";
	
	public static void main(String[] args) {
		
		double now = System.nanoTime();
		Data.init(JSON_FILE_NAME);
		System.out.println((System.nanoTime() - now) / 1000000000);
		
		
		Object prod = Data.searchByBarcode(Long.parseLong("978032"));
		if (prod != null) {
			System.out.println(prod.toString());
			
			System.out.println();
		}
		Product[] ps = Data.searchByTitle("algorithms (4th edition)", 10);
		for (Product p : ps) System.out.println(p);
	}
}