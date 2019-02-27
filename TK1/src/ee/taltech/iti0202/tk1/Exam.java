package ee.taltech.iti0202.tk1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Exam {
    public static List<Integer> evenOdd(List<Integer> input) {
        List<Integer> answer = new ArrayList<>();
        for (Integer even : input) {
            if (even % 2 == 0) {
                answer.add(even);
            }
        }
        for (Integer odd : input) {
            if (odd % 2 == 1) {
                answer.add(odd);
            }
        }

        return answer;
    }

    public static int loneSum(int a, int b, int c) {
        if (a == b && b == c) {
            return 0;
        }

        if (a == b) {
            return c;
        }

        if (a == c) {
            return b;
        }

        if (b == c) {
            return a;
        }

        return a + b + c;
    }

    public static String getSandwich(String str) {
        String[] answer = str.split("bread");
        if (answer.length == 3) {
            return answer[1];
        }
        return "";
    }

    public static Map<String, String> topping(Map<String, String> map) {
        if (map.containsKey("ice cream")) {
            map.put("ice cream", "cherry");
        }
        if (map.containsKey("bread")) {
            map.put("bread", "butter");
        } else {
            map.putIfAbsent("bread", "butter");
        }

        return map;
    }

    public static void main(String[] args) {
        //List<Integer> vars = new ArrayList(){}
        // System.out.println(evenOdd());
        // evenOdd([1, 0, 1, 0, 0, 1, 1]) // → [0, 0, 0, 1, 1, 1, 1]
        // evenOdd([3, 3, 2]) // → [2, 3, 3]
        // evenOdd([2, 2, 2]) // → [2, 2, 2]
        // loneSum(1, 2, 3) // → 6
        // loneSum(3, 2, 3) // → 2
        // loneSum(3, 3, 3) // → 0
        // getSandwich("breadjambread") // → "jam"
        // getSandwich("xxbreadjambreadyy") // → "jam"
        // getSandwich("xxbreadyy") // → ""
        // topping({"ice cream": "peanuts"}) // → {"bread": "butter", "ice cream": "cherry"}
        // topping({}) // → {"bread": "butter"}
        // topping({"pancake": "syrup"}) // → {"bread": "butter", "pancake": "syrup"}
    }
}
