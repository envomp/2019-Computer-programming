
package ee.taltech.iti0202.sentence;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Sentence class represent words and punctuation.
 */
public class Sentence {
    // TODO: add some private variable(s) here
    private String sentence;

    /**
     * Given string is treated as possible sentence.
     * <p>
     * Ignore all duplicate whitespaces.
     * If a word ends with ".", "!" or "?" treat it as punctuation.
     * No words can follow after punctuation - just ignore those.
     *
     * @param text Sentence as string
     */

    private void Helper(String text){
        boolean lastWasSpace = false;
        boolean startOfString = true;
        StringBuilder noDupes = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                if (startOfString) {
                    lastWasSpace = true;
                } else if (!lastWasSpace) {
                    lastWasSpace = true;
                    noDupes.append(c);
                }
            } else {
                lastWasSpace = false;
                startOfString = false;
                noDupes.append(c);
            }
        }
        sentence = noDupes.toString();
        sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

    public Sentence(String text) {
        Helper(text);
    }

    public Sentence() {
        sentence = "";
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
        try {
            if (!(sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?"))) {
                sentence = sentence.toLowerCase().replaceFirst(Pattern.quote(word), "");
                Helper(sentence);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
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
        if (sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?")) {
            return false;
        } else if (sentence.isEmpty()) {
            sentence += word;
        } else {
            sentence += " " + word;
        }

        return true;
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
        if (sentence.equals("") || sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?")) {
            return false;
        }
        sentence += punctuation;
        return true;
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
        if (sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?")) {
            while (sentence.endsWith(".") || sentence.endsWith("!") || sentence.endsWith("?")) {
                sentence = sentence.substring(0, sentence.length() - 1);
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return sentence;
    }

    @Override
    public boolean equals(Object obj) {
        return sentence.equals(obj);
    }


    public static void main(String[] args) {
        Sentence s1 = new Sentence("hello world");
        System.out.println(s1);  // Hello world...
        Sentence s2 = new Sentence("Hello world");
        System.out.println(s2); // Hello world...
        System.out.println(s1.equals(s2)); // true

        Sentence s3 = new Sentence("Hello world!");
        System.out.println(s3); // Hello world!
        System.out.println(s1.equals(s3)); // false
        System.out.println();
        System.out.println();
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
        s6.removePunctuation();
        s6.removeWord("hello");
        System.out.println(s6); // World...
        s6.removeWord("world");
        System.out.println(s6);
        System.out.println(s6.addPunctuation("wat?"));  // false
        s6.addWord("??");
        s6.addPunctuation("hello");
        System.out.println(s6);  // ??hello

    }
}

