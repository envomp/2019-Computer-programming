package ee.taltech.iti0202.sum100;

import java.util.*;

public class Sum100 {

    public static final int numbersInList = 9;

    public static List<String> calcSums() {
        return possibilities(new char[]{' ', '-', '+'}, numbersInList, "", new ArrayList<>());
    }

    public static List<String> possibilities(char[] c, int n, String start, List<String> answers) {
        if (start.length() >= n) {
            int i = numbersInList;
            List<Integer> curIteration = new ArrayList<>();
            curIteration.add(0);
            for (char operator : start.toCharArray()) {
                switch (operator) {
                    case ' ':
                        int t = curIteration.remove(curIteration.size() - 1);
                        if (t == 0) {
                            curIteration.add(i);
                        } else {
                            curIteration.add((int) (t + i * Math.pow(10, String.valueOf(t).length())));
                        }
                        break;
                    case '+':
                        curIteration.add(i);
                        break;
                    case '-':
                        curIteration.add(curIteration.remove(curIteration.size() - 1) * -1);
                        curIteration.add(i);
                        break;
                    default:
                        break;
                }
                i--;
            }
            if (curIteration.stream().mapToInt(Integer::intValue).sum() == 100) {
                if (curIteration.get(0) == 0) curIteration.remove(0);
                Collections.reverse(curIteration);
                StringBuilder sb = new StringBuilder();
                for (int elem : curIteration) {
                    if (elem > 0 && !String.valueOf(elem).startsWith("1")) sb.append("+");
                    sb.append(elem);
                }
                if (!answers.contains(sb.toString())) {
                    answers.add(sb.toString());
                }
            }
        } else {
            for (char x : c) {
                possibilities(c, n, start + x, answers);
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        System.out.println(calcSums());
    }
}