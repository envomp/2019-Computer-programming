
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
            values.putIfAbsent(rules.get(FIRSTOPERAND), 0);
            values.putIfAbsent(rules.get(VARIABLE), 0);

            if (equation(values.get(rules.get(FIRSTOPERAND)), rules.get(ACTION).toLowerCase(),
                    Integer.parseInt(rules.get(SECONDOPERAND)))) {
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
        Map<String, Integer> res = compute(
                "a dec 0 if a < 0"
        );
        System.out.println(res);
    }
}
