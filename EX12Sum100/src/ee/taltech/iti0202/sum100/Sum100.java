package ee.taltech.iti0202.sum100;

import java.util.ArrayList;
import java.util.List;

public class Sum100 {

    public static final int NUMBERS_IN_LIST = 9;

    public static List<String> calcSums() {
        return possibilities(new char[]{' ', '+', '-'}, NUMBERS_IN_LIST, "", new ArrayList<>());
    }

    public static List<String> possibilities(char[] c, int n, String start, List<String> answers) {
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
