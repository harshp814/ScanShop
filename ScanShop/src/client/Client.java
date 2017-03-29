package client;

import adts.Product;
import file_processing.JsonReader;
import file_processing.MakeProductArray;
import searching.BinarySearch;
import sorting.Merge;
import sorting.Trie;

public class Client {
	
	public static void main(String[] args) {
		
		
		
		Product[] parr = MakeProductArray.make(JsonReader.read());
		
		
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