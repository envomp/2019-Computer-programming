package ee.ttu.iti0202.casino.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CasinoRunner {

    public CasinoRunner() {

    }

    public List<Integer> makeStake(int sum, List<Integer> stack) {
        int N = (int) Math.pow(2d, (double) stack.size());
        for (int i = 1; i < N; i++) {
            String code = Integer.toBinaryString(N | i).substring(1);
            List<Integer> temp = IntStream.range(0, stack.size())
                    .filter(j -> code.charAt(j) == '1')
                    .mapToObj(stack::get)
                    .collect(Collectors.toList());
            if (temp.stream().mapToInt(Integer::intValue).sum() == sum) {
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        CasinoRunner cr = new CasinoRunner();

        cr.makeStake(100, List.of(10, 30, 20, 50, 50)); // -> // [50, 50] või [50, 20, 30]
        cr.makeStake(50, List.of(10, 30, 20, 20, 10)); // -> // [30, 20] või [10, 20, 20] või [30, 10, 10]
        cr.makeStake(50, List.of(10, 30, 20, 10, 10)); // -> // [30, 20] või [30, 10, 10] või [20, 10, 10, 10]

    }
}
