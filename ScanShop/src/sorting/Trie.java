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
	 * Node class that Letter and Suffix extend from.
	 * @author Group 1
	 */
	private class Node {
		
		protected Node pre;
		protected ArrayList<Integer> words;
		
		/**
		 * Create a new Node by providing a predecessor Node.
		 * @param pre Node representing the Node that links to this Node.
		 */
		public Node(Node pre) {
			this.pre = pre;
			words = new ArrayList<Integer>();
		}
	}
		
	/**
	 * Private Letter class that acts as a node in a graph.
	 * @author Group 1
	 */
	private class Letter extends Node {
		
		private char letter;
		private ArrayList<Node> next;
		
		/**
		 * Create a new letter with the given character and predecessor.
		 * @param letter char representing the character this Letter will be.
		 * @param pre Letter object representing the Letter that references this Letter.
		 */
		public Letter(char letter, Letter pre) {
			super(pre);
			this.letter = letter;
			next = new ArrayList<Node>();
		}
		
		public String toString() { return Character.toString(letter); }
	}
	
	/**
	 * Private Suffix class hat acts as a node in a graph.
	 * @author Group 1
	 *
	 */
	private class Suffix extends Node {

		private String suff;
		
		/**
		 * Create a Suffix object from a String and a predecessor Node.
		 * @param suff String representing the Suffix.
		 * @param pre Node representing the Node that links to this Node.
		 */
		public Suffix(String suff, Node pre) {
			super(pre);
			this.suff = suff;
		}
		
		public String toString() { return suff; }

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
		
		Letter cur = root;
		
		int index = 0;
		int length = word.length();
		
		// TRACE TRIE
		while (index < length) {
			boolean skip = true;
			for (Node n : cur.next ) {
				if (n instanceof Letter) {
					if (((Letter) n).letter == word.charAt(0)) {
						cur = (Letter) n;
						word = word.substring(1);
						skip = false;
						break;
					}
				}
			}
			if (skip) break;
			index++;
		}
		
		// If the word has been found within the initial trace, add
		// the word to the correct node.
		if (word.length() == 0) {
			cur.words.add(ind);
			return;
		}
		
		// Check for any suffix that contains a first letter to the
		// leftover word.
		for (Node n : cur.next) {
			if (n instanceof Suffix) {
				// If the suffix perfectly matches the rest of the word,
				// just add the index of the word to the node because we
				// have found duplicate data.
				if (((Suffix) n).suff.equals(word)) {
					n.words.add(ind);
					return;
				}
				// If a suffix with a matching first letter is found,
				// break the suffix recursively.
				if (((Suffix) n).suff.charAt(0) == word.charAt(0)) {
					breakSuffix((Suffix) n, word, ind);
					return;
				}
			}
		}
		
		// If no matching suffix was found, add a suffix.
		Suffix newSuffix = new Suffix(word, cur);
		newSuffix.words.add(ind);
		cur.next.add(newSuffix);
		
	}
	
	private void breakSuffix(Suffix n, String word, int ind) {
		// TODO break suffix recursively.
		
		// Remember the base node where the breaking begins.
		Letter base = (Letter) n.pre;
		// Remove the suffix from it's predecessors "next".
		base.next.remove(n);
		// Remember the current predecessor Letter as we break - start at base.
		Letter curPre = base;
		
		// Calculate the maximum times we can break.
		int maxBreak = Math.min(n.suff.length(), word.length());
		// Keep track of index.
		int index = 0;
		
		while (index < maxBreak && n.suff.charAt(0) == word.charAt(0)) {
			
			// Create new letter and link it to the predecessor.
			Letter newLetter = new Letter(word.charAt(0), curPre);
			curPre.next.add(newLetter);
			
			// Shorten the suffix and word.
			if (n.suff.length() == 1) n.suff = "";
			else n.suff = n.suff.substring(1);
			if (word.length() == 1) word = "";
			else word = word.substring(1);
			
			// Move the current predecessor to the new Letter made.
			curPre = newLetter;
			index++;
		}
		
		// If the leftover suffix has more than one letter in it, just add
		// it back on to the end of curPre.
		if (n.suff.length() > 1) {
			n.pre = curPre;
			curPre.next.add(n);
		}
		// If the leftover suffix has only one letter, create a letter object
		// and add that on to the end of curPre.
		else if (n.suff.length() > 0) {
			Letter newLetter = new Letter(n.suff.charAt(0), curPre);
			newLetter.words.addAll(n.words);
			curPre.next.add(newLetter);
		}
		// If the leftover suffix has no letters, add the suffixes words to the
		// curPre.
		else {
			curPre.words.addAll(n.words);
		}
		// If the leftover word has more than one letter in it, create a new
		// suffix object and add it onto the end of curPre.
		if (word.length() > 1 ) {
			Suffix newSuffix = new Suffix(word, curPre);
			newSuffix.words.add(ind);
			curPre.next.add(newSuffix);
		}
		// If the leftover word has only one letter, create a letter object 
		// and add that on to the end of curPre.
		else if (word.length() > 0) {
			Letter newLetter = new Letter(word.charAt(0), curPre);
			newLetter.words.add(ind);
			curPre.next.add(newLetter);
		}
		// If the leftover word has no letters, add the word index to the curPre.
		else {
			curPre.words.add(ind);
		}
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
	
	public void printTree() {
		_printTree_(root, "");
	}
	
	private void _printTree_(Node node, String buff) {
		
		if (node instanceof Suffix) System.out.println(buff + ((Suffix) node).suff + " : " + node.words.size());
		else  {
			System.out.println(buff + ((Letter) node).letter + /*" : " + node.getClass() +*/ " : " + node.words.size());

			for (Node n : ((Letter) node).next) {
				_printTree_(n, buff + " ");
			}
		}
	}
	
	public void printData() {
		_printData_(root, "");
	}
	
	private void _printData_(Node node, String word) {
	
		for (int i = 0; i < node.words.size(); i++) {
			System.out.println(word + node.toString() + " : " + node.words.size());
		}
		if (node instanceof Suffix) return;
		for (Node n : ((Letter) node).next) {
			_printData_(n, word + node.toString());
		}
		
	}
	
}
