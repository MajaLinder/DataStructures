
import java.util.Comparator;

public class Term {
    private String word;
    private long weight;

    // Initializes a term with a given word and weight.
    public Term(String word, long weight) {
        this.word = word;
        this.weight = weight;
    }

    // Gets the word.
    public String getWord() {
        return word;
    }

    // Gets the weight.
    public long getWeight() {
        return weight;
    }

    // Extracts a prefix from the word.
    // If `len` is larger than the word length, the prefix is the entire word.
    public String getPrefix(int len) {
        int wordLength = getWord().length();

        if (len > wordLength) {
            return getWord();
        }
        else {
            return getWord().substring(0, len);
        }
    }

    // Compares two terms in case-insensitive lexicographic order.
    //DONE
    public static final Comparator<Term> byLexicographicOrder =
            (one, other) -> one.getWord().compareToIgnoreCase(other.getWord()); //O(1)

    // Compares two terms in descending order by weight.
    // DONE
    public static final Comparator<Term> byReverseWeightOrder =
            (one, other) -> Long.compare(other.getWeight(), one.getWeight()); //O(1)


    // This method returns a comparator that compares the two terms in case-insensitive
    // lexicographic order, but using only the first k characters of each word.
    // DONE
    public static Comparator<Term> byPrefixOrder(int k) {
        // Hint: use getPrefix and byLexicographicOrder.
        return (one, other) -> one.getPrefix(k).compareToIgnoreCase(other.getPrefix(k));
    }

    /*
    // If you are stuck with creating comparators, here is a silly example that considers all integers equal:
    public static final Comparator<Integer> example0 = new Comparator<Integer>() {
        public int compare(Integer a, Integer b) {
            return 0;
        }
    };

    // And here is the same example using functional syntax:
    public static final Comparator<Integer> example1 = (a, b) -> {
        return 0;
    };

    // This is the same as the following:
    public static final Comparator<Integer> example2 = (a, b) -> 0;
    */

    // Returns a string representation of this term in the following format:
    // the weight, followed by whitespace, followed by the word.
    public String toString() {
        return String.format("%12d    %s", this.getWeight(), this.getWord());
    }

}
