package ee.taltech.iti0202.kt1;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    public static List<Integer> between4(List<Integer> numbers) {
        List<Integer> flags = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 4) {
                flags.add(i);
            }
        }
        if (flags.size() == 0) {
            return numbers;
        }

        if (flags.size() == 1) {
            return numbers.subList(0, flags.get(0));
        }
        return numbers.subList(flags.get(flags.size() - 2) + 1, flags.get(flags.size() - 1));
    }

    public static String mixString(String a, String b) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            if (a.length() > i) {
                answer.append(a.charAt(i));
            }

            if (b.length() > i) {
                answer.append(b.charAt(i));
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(4);
        nums.add(1);
        nums.add(1);
        nums.add(4);
        nums.add(1);
        System.out.println(between4(nums));
        System.out.println(mixString("aaa", "bbbb"));
    }
}
