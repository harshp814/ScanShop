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
    public static int indexOf(Product[] a, int asin) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid].asin() < asin) 
            	hi = mid - 1;
            else if (a[mid].asin() > asin) 
            	lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    
    /**
     * Searches for a product using price
     * @param a The input array of products
     * @param price The price of the product 
     * @return The index of the product or -1 if no such product exists 
     */
    public static int indexOf(Product[] a, String title) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[hi].title().contains(title)) 
            	hi = mid - 1;
            else if (a[mid].title().contains(title)) 
            	lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
