import java.util.Random;

/**
 *  For additional documentation on this implementation of quicksort,
 *  see <a href="https://algs4.cs.princeton.edu/23quick">Section 2.3</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Quick {
    
    /**
     * Instantiate the quicksort algorithm with the given options.
     * @param shuffleFirst         If true, shuffle the array before quicksorting it.
     * @param useMedianOfThree     If true, use the median-of-three technique for pivot selection.
     *                             In the recursive call for quicksorting a[lo..hi],
     *                             the first element a[lo] is used as pivot by default.
     *                             Instead, this uses the median of the first, middle, and last element of a[lo..hi].
     * @param insertionSortCutoff  Switch to insertion sort in the recursive call for quicksorting a[lo..hi]
     *                             once the size of a[lo..hi] is less than the given cutoff value.
     */
    public Quick(boolean shuffleFirst, boolean useMedianOfThree, int insertionSortCutoff) {
        this.shuffleFirst = shuffleFirst;
        this.useMedianOfThree = useMedianOfThree;
        this.insertionSortCutoff = insertionSortCutoff;
    }

    private boolean shuffleFirst;
    private boolean useMedianOfThree;
    private int insertionSortCutoff;

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */


        public void sort ( int[] a){

            if (shuffleFirst) {
                shuffle(a);
            }

            sort(a, 0, a.length - 1);
            assert Insertion.isSorted(a);
        }

    // Quicksort the subArray a[lo..hi].
    // This is the recursive workhorse of the algorithm.
    private void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
            int distance = hi - lo;
            if (distance < insertionSortCutoff) {
                Insertion.sort(a, lo, hi);
                return;
            }

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert Insertion.isSorted(a, lo, hi);
    }

    // Partition the subArray a[lo..hi] so that
    //   a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private int partition(int[] a, int lo, int hi) {

        if (useMedianOfThree) {

            int distance = hi + lo;
            int middle = distance / 2;

            int pivot = medianOfThree(a, lo, middle, hi);
            exchange(a, pivot, lo); //here!
        }

        int i = lo;
        int j = hi + 1;
        
        // a[lo] is used as pivot.
        int pivot = a[lo];

        // a[lo] is unique largest element.
        while (a[++i] < pivot)
            if (i == hi) {
                exchange(a, lo, hi);
                return hi;
            }

        // a[lo] is unique smallest element.
        while (pivot < a[--j])
            if (j == lo + 1)
                return lo;

        // the main loop
        while (i < j) {
            exchange(a, i, j);
            while (a[++i] < pivot);
            while (pivot < a[--j]);
        }

        // Put pivot item v at a[j].
        exchange(a, lo, j);

        // Now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi].
        return j;
    }

   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // Exchange a[i] and a[j].
    private static void exchange(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Return the index of the median element among a[i], a[j], and a[k].
    private static int medianOfThree(int[] a, int i, int j, int k) {

        if (((a[i] <= a[j]) && (a[k] <= a[i])) || ((a[i] >= a[j]) && (a[i] <= a[k]))) {
            return i;
        } else if (((a[j] <= a[i]) && (a[k] <= a[j])) || ((a[j] >= a[i]) && (a[k] >= a[j]))) {
            return j;
        }else {
            return k;
        }
    }


        // Shuffle an array, putting its contents in a random order.
    private static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i + random.nextInt(a.length - i); // uniformly distributed in [i, a.length)
            exchange(a, i, j);
        }
    }

    // Use a fixed random number seed to make testing reproducible.
    private static Random random = new Random(314159265);

}
