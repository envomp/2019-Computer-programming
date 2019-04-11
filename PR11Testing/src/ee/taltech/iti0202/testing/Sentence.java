
package ee.taltech.iti0202.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sentence class represent words and punctuation.
 */
public class Sentence {
    private List<String> words = new ArrayList<>();
    private String ending = "";

    /**
     * Given string is treated as possible sentence.
     * <p>
     * Ignore all duplicate whitespaces.
     * If a word ends with ".", "!" or "?" treat it as punctuation.
     * No words can follow after punctuation - just ignore those.
     *
     * @param text Sentence as string
     */
    public Sentence(String text) {
        for (String word : text.split(" ")) {
            if (word.length() > 0 && !word.matches(".*[.!?]$")) {
                words.add(word);
            } else if (word.length() > 0) {
                words.add(word.substring(0, word.length() - 1));
                ending = word.substring(word.length() - 1);
                break;
            }
        }
        if (text.length() < 2) {
            words = new ArrayList<>();
            ending = "";
        }

    }

    public Sentence() {
    }

    public static void main(String[] args) {
        System.out.println(new Sentence("Th.is i!s a tes?t but it en.ds here. yes?"));
        Sentence s1 = new Sentence("hello world");
        System.out.println(s1);  // Hello world...
        Sentence s2 = new Sentence("Hello world");
        System.out.println(s2); // Hello world...
        System.out.println(s1.equals(s2)); // true

        Sentence s3 = new Sentence("Hello world!");
        System.out.println(s3); // Hello world!
        System.out.println(s1.equals(s3)); // false


        Sentence s4 = new Sentence("Hi! Ignore those.");
        System.out.println(s4); // Hi!
        Sentence s5 = new Sentence("so.me po.in.ts he,re but only end counts. yes?");
        System.out.println(s5); // So.me po.in.ts he,re but only end counts.

        Sentence s6 = new Sentence();
        s6.addWord("hello");
        System.out.println(s6);  // Hello...
        s6.addWord("world");
        System.out.println(s6);  // Hello world...
        s6.addPunctuation("??");
        System.out.println(s6);  // Hello world??
        System.out.println(s6.addWord("NO"));  // false
        System.out.println(s6.addPunctuation("."));  // false
        s6.removePunctuation();
        s6.removeWord("hello");
        System.out.println(s6); // World...
        s6.removeWord("world");
        System.out.println(s6);
        System.out.println(s6.addPunctuation("wat?"));  // false
        s6.addWord("??");
        s6.addPunctuation("hello");
        System.out.println(s6);  // ??hello

        Sentence s7 = new Sentence(" hello     world    yes?");
        System.out.println(s7);  // Hello world yes?
        System.out.println(s7.addWord("CANNOT"));  // false
    }

    /**
     * Removes the first occurrence of the specified word from this sentence, if it is present.
     * If the word is not in the sentence, returns false.
     * If the sentence already has punctuation, returns false (nothing is removed).
     * Otherwise removes the word and returns true.
     *
     * @param word Word to be removed.
     * @return Whether word was in the sentence and removed.
     */
    public boolean removeWord(String word) {
        if (words.contains(word) && ending.equals("")) {
            words.remove(word.toLowerCase());
            return true;
        }
        return false;
    }

    /**
     * Adds word to sentence. The word can be any non-empty string without spaces.
     * If the sentence has punctuation added (either by the string in constructor
     * or by addPunctuation method), method return false and word is not added.
     * Otherwise the methods returns true and word is added to sentence.
     *
     * @param word Non-empty string without spaces.
     * @return Whether word was added to sentence (false if sentence has punctuation).
     */
    public boolean addWord(String word) {
        if (word.length() > 0 && !word.contains(" ") && ending.equals("")) {
            words.add(word);
            return true;
        }
        return false;
    }

    /**
     * Adds punctuation to the sentence.
     * <p>
     * The sentence can have only one punctuation. When trying to add second, method should return false.
     * If there are no words in the sentence, punctuation cannot be added.
     * If punctuation is added, no more words can be added.
     *
     * @param punctuation Punctuation string (e.g. "!")
     * @return Whether punctuation was added (false if sentence already had punctuation).
     */
    public boolean addPunctuation(String punctuation) {
        if (words.size() > 0 && ending.equals("")) {
            ending = punctuation;
            return true;
        }
        return false;
    }

    /**
     * Removes punctuation.
     * <p>
     * If punctuation is not yet added, the method returns false.
     * If punctuation has been added, it is removed.
     * After removing the punctuation, words can be added.
     *
     * @return Whether punctuation was removed (false if there was no punctuation).
     */
    public boolean removePunctuation() {
        if (!ending.equals("")) {
            ending = "";
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String sentence = String.join(" ", words) + ending;
        if (sentence.equals("")) return "";
        if (ending.equals("")) sentence = sentence + "...";
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return toString().equals(sentence.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(words, ending);
    }
}

