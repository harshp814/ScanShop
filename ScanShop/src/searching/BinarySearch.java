package searching;

import adts.Product;

public class BinarySearch {

    /**
     * Performs a Binary search for a product relative to barcode number.
     * @param ind Intger[] representing the index array.
     * @param prods Product[] representing the dataset.
     * @param id Long representing the barcode to search for.
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
