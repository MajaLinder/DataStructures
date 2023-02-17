import java.util.Arrays;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N) where N is the number of dictionary terms
    private void sortDictionary() {

        Arrays.sort(dictionary, Term.byLexicographicOrder);

    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {

        Term term = new Term(prefix, 0);

        int firstIndexOf = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        int lastIndexOf = RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));

        if (firstIndexOf == -1 || lastIndexOf == -1) {

            System.out.println("There are no matches available.");

            return new Term[0];

        }else {

            Term[] terms = Arrays.copyOfRange(dictionary, firstIndexOf, lastIndexOf +1);

            Arrays.sort(terms, Term.byReverseWeightOrder);

            return terms;
        }
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        Term term = new Term(prefix, 0);

        int firstIndexOf = RangeBinarySearch.firstIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));
        int lastIndexOf = RangeBinarySearch.lastIndexOf(dictionary, term, Term.byPrefixOrder(prefix.length()));

        if (firstIndexOf == -1 || lastIndexOf == -1) {

            System.out.println("There are no matches available.");

            return 0;
        } else {
            return ((lastIndexOf +1) - (firstIndexOf));
        }
    }
}