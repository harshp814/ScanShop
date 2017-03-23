package sorting;

public class Merge {

    private Merge() { }
    
    /**
     * Sorts the array from lowest to highest value
     * @param a The input array
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }
    
    /**
     * Merges together two sorted subarrays
     * @param a The input array
     * @param aux Auxiliary array to hold values
     * @param lo The lowest index of the subarray
     * @param mid The middle index of the subarray
     * @param hi The highest index of the subarray
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    	// making sure the two subarrays are sorted
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        // copying to aux
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merging to initial array
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
        
        // making sure the array is now sorted
        assert isSorted(a, lo, hi);
    }
    
    /**
     * Sorts an array bottom-up by recursively merging sorted subarrays
     * @param a Input array
     * @param aux Auxiliary array to temporarily hold values
     * @param lo The leftmost index of the current subarray
     * @param hi The rightmost index of the current subarray
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i].compareTo(a[i-1]) < 0) return false;
        return true;
    }
    
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
    	Integer[] arr = {5, 4, 3, 2, 1};
    	Merge.sort(arr);
    	for (int i = 0; i < arr.length; i++) {
    		System.out.println(arr[i]);
    	}
    }
}
