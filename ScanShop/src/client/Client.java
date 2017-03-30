package client;

import adts.Product;
import file_processing.Data;

public class Client {
	
	private final static String JSON_FILE_NAME = "amazon_rawdata.json.gz";
	
	public static void main(String[] args) {
		
		double now = System.nanoTime();
		Data.init(JSON_FILE_NAME);
		System.out.println((System.nanoTime() - now) / 1000000000);
		
		Product p = Data.searchByBarcode(Long.parseLong("9780321573513"));
		
		System.out.println(p.toString());
	}
}