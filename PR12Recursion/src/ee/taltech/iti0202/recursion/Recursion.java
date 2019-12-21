package ee.taltech.iti0202.recursion;

public class Recursion {

    /**
     * Find the text between the first and last parenthesis.
     *
     * @param word a random word
     * @return content between first and last parenthesis
     */
    public static String parentheses(String word) {
        if (word.length() < 2) {
            return "";
        } else if (word.charAt(0) == '(' && word.charAt(word.length() - 1) == ')') {
            return word;
        } else if (word.charAt(0) != '(') {
            return parentheses(word.substring(1));
        } else {
            return parentheses(word.substring(0, word.length() - 1));
        }
    }

    /**
     *
     * Remove every neighbouring duplicate char in the string recursively.
     *
     * @param word a word with duplicates
     * @return a word without any duplicates
     */
    public static String removeDuplicates(String word) {
        if (word.length() < 2) {
            return word;
        }
        if (word.charAt(0) == word.charAt(1)) {
            return removeDuplicates(word.substring(1));
        }
        return word.charAt(0) + removeDuplicates(word.substring(1));
    }

    /**
     * Remove any char that isn't in the Pseudo Hawaiian pidgin language.
     *
     * @param word a word that may contain other chars
     * @return a word where only Hawaiian pidgin chars exist
     */
    public static String pidginfy(String word) {
        if (word.length() == 0) {
            return "";
        }
        if (!word.substring(0, 1).toLowerCase().matches("[ aeiouhklmnpwr'\"āōū.,!?]")) {
            return pidginfy(word.substring(1));
        }
        return word.charAt(0) + pidginfy(word.substring(1));
    }
}
