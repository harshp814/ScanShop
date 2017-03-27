package adts;


import java.util.Iterator;
import java.util.NoSuchElementException;

// A Bag is a data structure that holds a list of items. 
public class Bag<Item> implements Iterable<Item> {

	// The head node (last item inserted into the bag - if null bag is empty)
	private Node head;

	// The size of the bag. Easy peasy stuff.
	private int size;

	// Typical private node class - unidirectional
	private class Node<Item> {
		private Node next;
		private Item val;

		public Node(Item val, Node next) {
			this.val = val;
			this.next = next;
		}
	}

	// Bag constructor. Initializes a brand new, empty bag.
	public Bag() {
		head = null;
		size = 0;
	}

	// Add something to the bag. Make life interesting.
	public void add(Item val) {
		// Remember what the last head was.
		Node<Item> lastHead = head;
		// Set the head to a brand new head, which is our new value (val)
		// that points to our last head.
		head = new Node<Item>(val, lastHead);
		// Increase the bag size.
		size++;
	}

	// Is the bag empty? It is if the head is null!
	public boolean isEmpty() {
		return head == null;
	}

	// Get the size of the bag.
	public int size() {
		return size;
	}

	// Get an iterator for our bag. See the private BagIterator class.
	public Iterator<Item> iterator() {
		return new BagIterator<Item>(head);
	}

	// Although this looks scary, it isn't. This just allows us to return an
	// object that allows
	// the user to loop through the items in our bag. Simple stuff!
	private class BagIterator<Item> implements Iterator<Item> {

		// We're travelling through the bag items, so we need to remember where
		// we are.
		// At any given time, the current variable represents the "next" item a
		// user will
		// get if they call the next() method.
		private Node<Item> current;

		// Take our starting point and.. well.. start there.
		public BagIterator(Node<Item> start) {
			current = start;
		}

		// Is there a next item? There is if current is not null!
		public boolean hasNext() {
			return current != null;
		}

		// Get the next item in the bag.
		public Item next() {
			// Double check to make sure there is a next item.
			if (!hasNext())
				throw new NoSuchElementException();
			// Grab the current value (we will return this).
			Item valToReturn = current.val;
			// Set the next current before we return - this is important!
			current = current.next;
			// Return the value we grabbed earlier.
			return valToReturn;
		}

	}

}
