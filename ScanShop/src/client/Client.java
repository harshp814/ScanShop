package client;

import adts.Product;
import file_processing.JsonReader;
import file_processing.MakeProductArray;

public class Client {
	
	public static void main(String[] args) {
		Product[] parr = MakeProductArray.make(JsonReader.read());
		for (int i = 0; i < parr.length; i++) 
			System.out.println(parr[i].toString());
	}
}