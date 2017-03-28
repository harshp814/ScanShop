package sorting;

import java.util.ArrayList;

/**
 * Trie data structure used to store and search many Strings efficiently.
 * @author Group 1
 */
public class Trie {

	// Root of our Trie data structure (no predecessor, letter is a space).
	Letter root;
	
	/**
	 * Private letter class that acts as a node in a graph.
	 * @author Group 1
	 */
	private class Letter {
		
		// Each letter node holds a character, a pointer to the letter that refers to it,
		// an arraylist of letters that extend from it, and an arraylist of the indices of
		// the words that terminate at the Letter.
		private char letter;
		private Letter pre;
		private ArrayList<Letter> next;
		private ArrayList<Integer> words;
		
		/**
		 * Create a new letter with the given character and predecessor.
		 * @param letter char representing the character this Letter will be.
		 * @param pre Letter object representing the Letter that references this Letter.
		 */
		public Letter(char letter, Letter pre) {
			this.letter = letter;
			this.pre = pre;
			next = new ArrayList<Letter>();
			words = new ArrayList<Integer>();
		}
	}

	/**
	 * Instantiates a new Trie object with an empty root.
	 */
	public Trie() {
		root = new Letter(' ', null);
	}
	
	/**
	 * Adds the given word to the Trie.
	 * @param word String representing the word to add to the Trie.
	 */
	public void addWord(String word, int ind) {
		
		// TODO Add a word to the Trie.
		
		Letter cur = root;
		int index = 0;
		
		while (index < word.length()) {
			boolean cont = true;
			for (Letter l : cur.next) 
				if (l.letter == word.charAt(index)) {
					cur = l;
					cont = false;
					break;
				}
			if (cont) break;
			index++;
		}
		
		while (index < word.length()) {
			Letter next = new Letter(word.charAt(index), cur);
			cur.next.add(next);
			cur = next;
			index++;
		}
		
		cur.words.add(ind);
		
	}
	
	/**
	 * Searches the Trie data structure for the closest matches to the given query String.
	 * A close match is a String that is of shortest distance to the query in the Trie
	 * data structure. 
	 * @param query String representing the query to find the best matches for.
	 * @param num Integer representing the number of best matches to return.
	 * @return Integer array of the IDs of the Strings that best match the query. 
	 */
	public int[] getBestMatches(String query, int num) {
		int[] matches = new int[num];
		
		// TODO Get the best matches using BFS.
		
		return matches;

	}
	
	public void printData() {
		_printData_(root, "");
	}
	
	private void _printData_(Letter node, String buff) {
		
		if (node.words.size() > 0)
			System.out.println(buff);
		for (Letter l : node.next) {
			_printData_(l, buff + l.letter);
		}
	}
	
}
