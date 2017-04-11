package testing;

import static org.junit.Assert.*;
import java.util.Arrays;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import adts.Product;
import searching.BinarySearch;
import sorting.Merge;
import sorting.Trie;

public class Test_file {
	Product baseP;
	Product p2;
	Product p3;
	Product p4;
	Product p5;
	Product p6;

	@Before
	public void setUp() throws Exception {
		baseP = new Product("B00DR0PDNE","flower",29.45,170000000000L);
		p2 = new Product("B00DR0PDNE","fries",6.12,170000000000L);
		p3 = new Product("ODI9D5FG63","peanut",29.45,120000000000L);
		p4 = new Product("SG8WTH9WVV","cheese",29.45,784965123497L);
		p5 = new Product("B00DR0PDNE","cap",29.45,170000000000L);
		p6 = new Product("B00DR0PDNE","pepper",100.12,170000000000L);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProduct_compareto() {
		
		int result;
		//Check if base is bigger based on price
		result = baseP.compareTo(p2);
		assertEquals(result,1);
		//Check if base is bigger based on id
		result = baseP.compareTo(p3);
		assertEquals(result,1);
		//Check if base is smaller based on id
		result = baseP.compareTo(p4);
		assertEquals(result,-1);
		//Check if base is equal to other product
		result = baseP.compareTo(p5);
		assertEquals(result,0);
		//Check if base is smaller based on price
		result = baseP.compareTo(p6);
		assertEquals(result,-1);
		
	
	}
	
	@Test
	public void testMerge() {
		Product[] prods = {baseP,p2,p3,p4,p5,p6};
		int[] index = {0,1,2,3,4,5};
		Merge.sort(index, prods);
		assertTrue(isSorted(index,prods));
		
		Product[] prods2 = {baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6};
		int[] index2 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		Merge.sort(index2, prods2);
		assertTrue(isSorted(index,prods));
		
		Product[] prods3 = {baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6
				,baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6,
				baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6,baseP,p2,p3,p4,p5,p6};
		int[] index3 = new int[54];
		
		for (int x = 0; x < index3.length; x++){
			index3[x]=x;
		}
		Merge.sort(index3, prods3);
		assertTrue(isSorted(index,prods));
		
		
		
	}
	
	@Test
	public void testTrie() {
		//Initialize trie
		Trie trie1 = new Trie();
		//Initialize reference array
		ArrayList<String> str1 = new ArrayList<String>();
		
		//Add words to trie
		trie1.addWord(baseP.title(),0);
		str1.add(baseP.title());
		
		trie1.addWord(p2.title(),1);
		str1.add(p2.title());
		
		trie1.addWord(p3.title(),2);
		str1.add(p3.title());
		
		trie1.addWord(p4.title(),3);
		str1.add(p4.title());
		
		trie1.addWord(p5.title(),4);
		str1.add(p5.title());
		
		trie1.addWord(p6.title(),5);
		str1.add(p6.title());
		
		

		//Attempt to find "flower"
		int [] w =trie1.getBestMatches("fl", 2);

		for (int y = 0; y < w.length; y++){
			if(str1.get(w[y]).equals("flower")){
				assertTrue(true);
				break;
			}else if (y == w.length-1){
				fail("could not find word");	
			}
		}
		
		//Attempt to find peanut
		w = trie1.getBestMatches("pea", 2);
		
		for (int y = 0; y < w.length; y++){
			if(str1.get(w[y]).equals("peanut")){
				assertTrue(true);
				break;
	
			}else if (y == w.length-1){
				fail("could not find word");	
			}
		}
		
		//Attempt to find word that is not in trie
		w =trie1.getBestMatches("pea", 2);

		for (int y = 0; y < w.length; y++){
			if(str1.get(w[y]).equals("tree")){
				fail("broken trie");
				break;
		
			}else if (y == w.length-1){
				assertTrue(true);
			}
		}
		
		//add more words to trie
		trie1.addWord("heap",6);
		str1.add("heap");
		
		trie1.addWord("hat",7);
		str1.add("hat");
		
		trie1.addWord("fat",8);
		str1.add("fat");
		
		//Attempt to find "heap"
		w = trie1.getBestMatches("hea", 2);
		
		for (int y = 0; y < w.length; y++){
			if(str1.get(w[y]).equals("heap")){
				assertTrue(true);
				break;
	
			}else if (y == w.length-1){
				fail("could not find word");	
			}
		}
		
		//Attempt to find "fat"
		w = trie1.getBestMatches("fa", 3);
		
		for (int y = 0; y < w.length; y++){
			if(str1.get(w[y]).equals("fat")){
				assertTrue(true);
				break;
	
			}else if (y == w.length-1){
				fail("could not find word");	
			}
		}

	}
	
	@Test
	public void testBinary() {
		Product[] prods = {baseP,p2,p3,p4,p5,p6};
		int[] index = {0,1,2,3,4,5};
		Merge.sort(index, prods);
		//Find an object present in the array
		Product ret = BinarySearch.searchID(index, prods, 784965123497L);
		assertEquals(ret.asin(),"SG8WTH9WVV");
		
		//Attempt to find an object not present in the array
		ret = BinarySearch.searchID(index, prods, 500000000000L);
		if (ret == null){
			assertTrue(true);
		}else{
			fail("Error in finding an object not present in list");
		}
		
	}
	
	private boolean isSorted(int[]intar,Product[]prodar){
		for(int x = 0; x < intar.length-1; x++){
			if(!(prodar[intar[x]].compareTo(prodar[intar[x+1]]) <= 0)){
				return false;
			}
		}
		return true;
	}
}
