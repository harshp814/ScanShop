package searching;

import adts.Product;

public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() { }

    /**
     * Searches for a product using asin number 
     * @param a The input array of products
     * @param asin The amazon standard identification number
     * @return The index of the product or -1 if no such product exists
     */
    public static String titleOf(Product[] a, String asin) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = a[mid].asin().compareTo(asin);
            if (cmp > 0) 
            	hi = mid - 1;
            else if (cmp < 0) 
            	lo = mid + 1;
            else return a[mid].title();
        }
        return null;
    }
}
