
package ee.taltech.iti0202.cpu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cpu {
    private static final int VARIABLE = 0;
    private static final int INCREASEORDECREASE = 1;
    private static final int BYHOWMUCH = 2;
    private static final int FIRSTOPERAND = 4;
    private static final int ACTION = 5;
    private static final int SECONDOPERAND = 6;

    public static Map<String, Integer> compute(String instructions) {
        HashMap<String, Integer> values = new HashMap<>();
        for (String elem : instructions.split("[\\r\\n]+")) {
            ArrayList<String> rules = new ArrayList<>(Arrays.asList(elem.split(" ")));
            if (!values.containsKey(rules.get(VARIABLE))) {
                values.put(rules.get(VARIABLE), 0);
            }
            if (!values.containsKey(rules.get(FIRSTOPERAND))) {
                values.put(rules.get(FIRSTOPERAND), 0);
            }
            if (equation(values.get(rules.get(FIRSTOPERAND)), rules.get(ACTION), Integer.parseInt(rules.get(SECONDOPERAND)))) {
                switch (rules.get(INCREASEORDECREASE)) {
                    case "inc":
                        values.put(rules.get(0), values.get(rules.get(0)) + Integer.parseInt(rules.get(BYHOWMUCH)));
                        break;
                    case "dec":
                        values.put(rules.get(0), values.get(rules.get(0)) - Integer.parseInt(rules.get(BYHOWMUCH)));
                        break;
                    default:
                        break;
                }
            }

        }
        return values;
    }


    private static boolean equation(int a, String operator, int b) {
        switch (operator) {
            case "==":
                return a == b;
            case ">=":
                return a >= b;
            case "<=":
                return a <= b;
            case "<":
                return a < b;
            case ">":
                return a > b;
            case "!=":
                return a != b;
            default:
                return false;

        }
    }


    public static void main(String[] args) {
        var res = compute(
                "b inc 5 if a > 1\n" +
                        "a inc 1 if b < 5\n" +
                        "c dec -10 if a >= 1\n" +
                        "c inc -20 if c == 10"
        );
        System.out.println(res); // {a=1, b=0, c=-10}

        res = compute(
                "b inc 7 if a > 4\n" +
                        "a inc 1 if c < 13\n" +
                        "c dec -10 if a >= 1\n" +
                        "c inc -20 if c == 10\n" +
                        "abc inc 100 if a != -23\n" +
                        "a inc 2 if a <= 0"
        );
        System.out.println(res); // {a=1, b=0, c=-10, abc=100}
    }

}
