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
		// an arraylist of letters that extend from it, and a counter for how many words terminate
		// at this character.
		private char letter;
		private Letter pre;
		private ArrayList<Letter> next;
		private int terminates;
		
		/**
		 * Create a new letter with the given character and predecessor.
		 * @param letter char representing the character this Letter will be.
		 * @param pre Letter object representing the Letter that references this Letter.
		 */
		public Letter(char letter, Letter pre) {
			this.letter = letter;
			this.pre = pre;
			terminates = 0;
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
	public void addWord(String word) {
		
	}
	
	
}
