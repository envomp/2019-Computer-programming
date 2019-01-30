package ee.taltech.iti0202.intro;

public class Introduction {

    /**
     * Method gets a string containing word Java in different cases. tere
     * Method must be case-insensitive to recognize all the Java words.
     *
     * @param stringWithJava the string to find words Java from.
     * @return The number of word Java occurrences.
     */
    public static int countJava(String stringWithJava) {
        return (stringWithJava.length() - stringWithJava.toLowerCase().replace("java", "").length()) / 4;

    }


    /**
     * Method gets an array of numbers and another number.
     * The second integer's value must equal to the number of its occurrences in the given array.
     *
     * @param inputNumbers int array
     * @param sneakyNumber int
     * @return true if sneakyNumber's value equals its number of occurrences
     */
    public static boolean doubleNumber(int[] inputNumbers, int sneakyNumber) {
        int amount = 0;
        for (int n: inputNumbers) {
            if (n == sneakyNumber) {
                amount++;
            }
        } return amount == sneakyNumber;
    }


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "ok" if both values equal at least 5.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return true if sneakyNumber's value equals its number of occurrences
     */
    public static String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne / valueTwo == 2 || valueTwo / valueOne == 2) {
            return "good";
        } else if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        } else {
        return "ok";
        }
    }

    public static void main(String[] args) {
        // static method we can call directly from static method (main)
        System.out.println(countJava("javaJavaJAVA"));  // 3
        System.out.println(countJava("JAVAabcJava"));  // 2
        System.out.println(countJava("HelloWorldIsLongerInJava"));  // 1
        System.out.println(countJava("Nothing here"));  // 0
        System.out.println();
        System.out.println(doubleNumber(new int[]{1, 4, 8, 2, 2, 3}, 2));
        System.out.println(doubleNumber(new int[]{3, 5, 4, 3, 2}, 3));
        System.out.println(doubleNumber(new int[]{0, 1, 1, 2, 1, 3, 4, 1}, 1));
        // try some other examples here
    }
}
