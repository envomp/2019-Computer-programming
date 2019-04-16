package ee.taltech.iti0202.sum100;


import java.util.*;

public class Sum100 {

    public static List<String> calcSums() {
        List<String> possibilities = possibilities(new char[]{' ', '-', '+'}, 9, "", new ArrayList<>());
        Set<String> set = new HashSet<>(possibilities);
        possibilities.clear();
        possibilities.addAll(set);
        return possibilities;
    }

    public static List<String> possibilities(char[] c, int n, String start, List<String> answers) {
        if (start.length() >= n) {
            int i = 9;
            List<Integer> cur_iteration = new ArrayList<>();
            cur_iteration.add(0);
            for (char operator : start.toCharArray()) {
                switch (operator) {
                    case ' ':
                        int t = cur_iteration.remove(cur_iteration.size() - 1);
                        if (t == 0)
                            cur_iteration.add(i);
                        else
                            cur_iteration.add((int) (t + i * Math.pow(10, String.valueOf(t).length())));
                        break;
                    case '+':
                        cur_iteration.add(i);
                        break;
                    case '-':
                        cur_iteration.add(cur_iteration.remove(cur_iteration.size() - 1) * -1);
                        cur_iteration.add(i);
                        break;
                }
                i--;
            }
            if (cur_iteration.stream().mapToInt(Integer::intValue).sum() == 100) {
                if (cur_iteration.get(0) == 0) cur_iteration.remove(0);
                Collections.reverse(cur_iteration);
                StringBuilder sb = new StringBuilder();
                for (int elem : cur_iteration) {
                    if (elem > 0 && !String.valueOf(elem).startsWith("1")) sb.append("+");
                    sb.append(elem);
                }
                answers.add(sb.toString());
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