package searching;

import adts.Product;

public class BinarySearch {

    /**
     * Searches for a product using asin number
     * @param ind The index array
     * @param prods The input array of products
     * @param id The amazon standard identification number
     * @return The index of the product or -1 if no such product exists
     */
    public static Product searchID(int[] ind, Product[] prods, long id) {
        int lo = 0;
        int hi = ind.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long cmp = prods[ind[mid]].id();
            if (cmp > id) 
            	hi = mid - 1;
            else if (cmp < id) 
            	lo = mid + 1;
            else return prods[ind[mid]];
        }
        return null;
    }
}
