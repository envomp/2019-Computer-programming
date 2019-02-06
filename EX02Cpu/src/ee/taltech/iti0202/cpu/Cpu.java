package ee.taltech.iti0202.cpu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cpu {

    public static Map<String, Integer> compute(String instructions) {
        HashMap<String, Integer> values = new HashMap<>();
        for (String elem : instructions.split("[\\r\\n]+")) {
            ArrayList<String> rules = new ArrayList<>(Arrays.asList(elem.split(" ")));
            if (!values.containsKey(rules.get(0))) {
                values.put(rules.get(0), 0);
            }
            if (equation(values.get(rules.get(4)), rules.get(5), Integer.parseInt(rules.get(6)))){
                switch (rules.get(1)){
                    case "inc": values.put(rules.get(0), values.get(rules.get(0)) + Integer.parseInt(rules.get(2)));
                    break;
                    case "dec": values.put(rules.get(0), values.get(rules.get(0)) - Integer.parseInt(rules.get(2)));
                    break;
                }
            }

        }
        return new HashMap<>();
    }


    private static boolean equation(int a, String operator, int b) {
        switch (operator) {
            case "==":  return a == b;
            case ">=":  return a >= b;
            case "<=":  return a <= b;
            case "<":  return a > b;
            case ">":  return a < b;
            case "!=":  return a != b;
            default: return false;

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