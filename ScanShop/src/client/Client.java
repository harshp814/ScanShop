package client;

import adts.Product;
import file_processing.Data;
import file_processing.JsonReader;
import searching.BinarySearch;
import sorting.Merge;

public class Client {
	
	private final static String JSON_FILE_NAME = "amazon_rawdata.json.gz";
	
	public static void main(String[] args) {
		
<<<<<<< HEAD
		Data.init(JSON_FILE_NAME);
=======
<<<<<<< HEAD
		
		
		Product[] parr = MakeProductArray.make(JsonReader.read());		
=======
		Product[] parr = Data.init(JSON_FILE_NAME);
>>>>>>> e2f5872c161bd09bb652ab7150004798890662be
>>>>>>> 77625bd070a351a31d9d91557e3bea8848f02f13
		
		
		//Merge.sort(parr);
		//for (int i = 0; i < parr.length; i++)
		//	System.out.println(parr[i].toString());
		//System.out.println(BinarySearch.titleOf(parr, "0000000868"));
		
		/*
		t.addWord("Daniel", 0);
		t.addWord("Danny", 1);
		t.addWord("DanielWolff", 2);
		t.addWord("DannyWolff", 3);
		t.addWord("DanWolff", 4);
		t.addWord("Wolff", 5);
		*/
		
		
		
	}
}