package ee.taltech.iti0202.sum100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sum100 {

    public static final int NUMBERS_IN_LIST = 9;

    public static List<String> calcSums() {
        return possibilities(new char[]{' ', '+', '-'}, NUMBERS_IN_LIST, "", new HashSet<>());
    }

    public static List<String> possibilities(char[] c, int n, String start, Set<String> answers) {
        if (start.length() >= n) {
            int i = 1;
            List<Integer> curIteration = new ArrayList<>();
            curIteration.add(0);
            int multiplier = 1;
            for (char operator : start.toCharArray()) {
                switch (operator) {
                    case ' ':
                        curIteration.add((i * multiplier + curIteration.remove(curIteration.size() - 1) * 10));
                        break;
                    case '+':
                        multiplier = 1;
                        curIteration.add(i);
                        break;
                    case '-':
                        multiplier = -1;
                        curIteration.add(i * -1);
                        break;
                    default:
                        break;
                }
                i++;
            }
            if (curIteration.stream().mapToInt(Integer::intValue).sum() == 100) {
                if (curIteration.get(0).equals(0)) curIteration.remove(0);
                answers.add(answerBuilder(curIteration));
            }
        } else {
            for (char x : c) {
                possibilities(c, n, start + x, answers);
            }
        }
        return new ArrayList<>(answers);
    }

    private static String answerBuilder(List<Integer> curIteration) {
        if (curIteration.size() == 0) {
            return "";
        } else {
            Integer elem = curIteration.remove(0);
            return (elem > 0 && !String.valueOf(elem).startsWith("1") ? "+" : "") + elem + answerBuilder(curIteration);
        }
    }

    public static void main(String[] args) {
        System.out.println(calcSums());
    }
}
