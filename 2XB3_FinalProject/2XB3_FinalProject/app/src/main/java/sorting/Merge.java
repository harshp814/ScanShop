package sorting;

import adts.Product;

public class Merge {

    private Merge() { }
    
    /**
     * Sorts the array from lowest to highest value
     * @param ind The index array
     * @param prods The input array
     */
    public static void sort(int[] ind, Product[] prods) {
        int[] aux = new int[ind.length];
        sort(ind, aux, 0, ind.length-1, prods);
    }
    
    /**
     * Merges together two sorted subarrays
     * @param a The input array
     * @param aux Auxiliary array to hold values
     * @param lo The lowest index of the subarray
     * @param mid The middle index of the subarray
     * @param hi The highest index of the subarray
     */
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi, Product[] prods) {

        // copying to aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merging to initial array
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (prods[aux[j]].compareTo(prods[aux[i]]) < 0) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
        
    }
    
    /**
     * Sorts an array bottom-up by recursively merging sorted subarrays
     * @param a Input array
     * @param aux Auxiliary array to temporarily hold values
     * @param lo The leftmost index of the current subarray
     * @param hi The rightmost index of the current subarray
     */
    private static void sort(int[] a, int[] aux, int lo, int hi, Product[] prods) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, prods);
        sort(a, aux, mid + 1, hi, prods);
        merge(a, aux, lo, mid, hi, prods);
    }

}
