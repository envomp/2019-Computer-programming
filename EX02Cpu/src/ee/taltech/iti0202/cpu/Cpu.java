package ee.taltech.iti0202.cpu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Cpu {

    public static Map<String, Integer> compute(String instructions) {
        Map<String, Integer> registers = new HashMap<>();
        ArrayList<String[]> listWithSplitedInstructions = splitAndPutInList(instructions, registers);

        for (String elem : instructions.split("[\\r\\n]+")) {
            ArrayList<String> rules = new ArrayList<>(Arrays.asList(elem.split(" ")));
            if (!registers.containsKey(rules.get(4))) {
                registers.put(rules.get(4), 0);
            }
            if (!registers.containsKey(rules.get(0))) {
                registers.put(rules.get(0), 0);
            }
        }

        for (int i = 0; i < listWithSplitedInstructions.size() - 1; i++) {
            if (i % 2 == 0 && checkCondition(listWithSplitedInstructions.get(i), registers)) {
                makeArithmeticOperations(registers, listWithSplitedInstructions.get(i + 1));
            }
        }
        return registers;
    }

    private static ArrayList<String[]> splitAndPutInList(String instructions, Map registers) {
        String[] splitedArray = instructions.split("\n");
        ArrayList<String[]> allSeperatedInstructions = new ArrayList<>();
        for (String oneCommand : splitedArray) {
            String[] commandSeperatedByIf = oneCommand.split("if ");
            String[] conditions = commandSeperatedByIf[1].split(" ");
            String[] allWordsSeperate = commandSeperatedByIf[0].split(" ");
            registers.put(allWordsSeperate[0], 0);
            allSeperatedInstructions.add(conditions);
            allSeperatedInstructions.add(allWordsSeperate);
        }
        return allSeperatedInstructions;
    }

    private static boolean checkCondition(String[] condition, Map registers) {
        String operator = condition[1];
        int num1 = (int) registers.get(condition[0]);
        int num2 = Integer.parseInt(condition[2]);
        switch (operator) {
            case ">":
                return num1 > num2;
            case "<":
                return num1 < num2;
            case "==":
                return num1 == num2;
            case ">=":
                return num1 >= num2;
            case "<=":
                return num1 <= num2;
            case "!=":
                return num1 != num2;
            default:
                return false;
        }
    }

    private static void makeArithmeticOperations(Map registers, String[] command) {
        String operation = command[1];
        String register = command[0];
        int num = Integer.parseInt(command[2]);
        int newValue;
        if (operation.equals("inc")) {
            newValue = (int) registers.get(register) + num;
        } else {
            newValue = (int) registers.get(register) - num;
        }
        registers.put(register, newValue);
    }

    public static void main(String[] args) {
        Map<String, Integer> res = compute(
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