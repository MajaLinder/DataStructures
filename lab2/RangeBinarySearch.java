
import java.util.Arrays;
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there is no matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int firstIndexOf(T[] a, T key, Comparator<T> comparator){
        int left = 0;  //left pointer
        int right = a.length -1;
        int index = -1; //return value.

        while(left <= right){
            int middle = (right + left) / 2;
            int result = comparator.compare(key, a[middle]);

            if(result == 0) {
                index = middle;
                right = middle -1;

            }else if(result > 0){
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return index;
    }



    // Returns the index of the *last* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there are is matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
        int left = 0;  //left pointer
        int right = a.length -1;
        int index = -1; //return value

        while(left <= right){
            int middle = (right + left) / 2;
            int result = comparator.compare(key, a[middle]);

            if(result == 0){
                index = middle;
                left = middle + 1;

            }else if(result > 0){
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return index;
    }

}