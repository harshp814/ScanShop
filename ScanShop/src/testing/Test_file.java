package testing;

import static org.junit.Assert.*;

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
		baseP = new Product("B00DR0PDNE","p1",29.45,170000000000L);
		p2 = new Product("B00DR0PDNE","p2",6.12,170000000000L);
		p3 = new Product("ODI9D5FG63","p3",29.45,120000000000L);
		p4 = new Product("SG8WTH9WVV","p3",29.45,784965123497L);
		p5 = new Product("B00DR0PDNE","p1",29.45,170000000000L);
		p6 = new Product("B00DR0PDNE","p6",100.12,170000000000L);
		
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
		Trie trie1 = new Trie();
		Trie trie2 = new Trie();
		
		trie1.addWord("add",0);
		trie1.addWord("alpha",1);
		trie1.addWord("air",2);
		trie1.addWord("break",3);
		trie1.addWord("bread",4);
		trie1.addWord("red",5);
		trie1.addWord("reed",6);
		trie1.addWord("read",7);
		
		int [] w =trie1.getBestMatches("re", 5);
		
		for(int x = 0; x < 100; x ++){
			trie2.addWord(Integer.toString(x), x);
		}
		
		trie2.printTree();
		
		
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
