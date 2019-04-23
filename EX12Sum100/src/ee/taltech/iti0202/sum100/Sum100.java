package ee.taltech.iti0202.sum100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Sum100 {

    public static final int NUMBERS_IN_LIST = 9;
    public static final int DESIRED_SUM = 100;

    public static List<String> calcSums() {
        return possibilities(new char[]{'+', '-', ' '}, NUMBERS_IN_LIST, "", new HashSet<>());
    }

    public static List<String> possibilities(char[] c, int n, String start, Set<String> answers) {
        if (start.length() >= n) {
            Stack<Integer> integerStack = new Stack<>();
            integerStack.add(0);
            List<Integer> curIteration = curIteratorBuilder(start.toCharArray(), 1, integerStack, 1);
            if (curIteration.stream().mapToInt(Integer::intValue).sum() == DESIRED_SUM) {
                if (curIteration.get(0).equals(0)) curIteration.remove(0);
                answers.add(answerBuilder(curIteration));
            }
        } else {
            summonThreads(c, n, start, answers, 0);
        }
        return new ArrayList<>(answers);
    }

    private static void summonThreads(char[] c, int n, String start, Set<String> answers, int i) {
        if (i != c.length) {
            possibilities(c, n, start + c[i], answers);
            summonThreads(c, n, start, answers, i + 1);
        }
    }

    private static List<Integer> curIteratorBuilder(char[] start, int i, Stack<Integer> curIteration, int multiplier) {
        if (i == 10) {
            return curIteration;
        }
        switch (start[i - 1]) {
            case ' ':
                curIteration.add((i * multiplier + curIteration.pop() * 10));
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
        return curIteratorBuilder(start, i + 1, curIteration, multiplier);
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
