package ee.ttu.iti0202.casino.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CasinoRunner {

    public List<Integer> makeStake(int sum, List<Integer> stack) {
        int N = (int) Math.pow(2d, (double) stack.size());
        return IntStream.range(1, N).mapToObj(i -> Integer.toBinaryString(N | i)
                .substring(1)).map(code -> IntStream.range(0, stack.size())
                .filter(j -> code.charAt(j) == '1')
                .mapToObj(stack::get)
                .collect(Collectors.toList())).filter(temp -> temp.stream().mapToInt(Integer::intValue)
                .sum() == sum).findFirst().orElse(null);
    }

    public static void main(String[] args) {

        CasinoRunner cr = new CasinoRunner();

        cr.makeStake(100, List.of(10, 30, 20, 50, 50)); // -> // [50, 50] või [50, 20, 30]
        cr.makeStake(50, List.of(10, 30, 20, 20, 10)); // -> // [30, 20] või [10, 20, 20] või [30, 10, 10]
        cr.makeStake(50, List.of(10, 30, 20, 10, 10)); // -> // [30, 20] või [30, 10, 10] või [20, 10, 10, 10]

    }
}
