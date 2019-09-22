import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Решение задачи "D - Пробки".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        BigInteger[] res = new BigInteger[n + 1];
        res[0] = BigInteger.ONE;
        res[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            res[i] = res[i - 2].add(res[i - 1]);
        }

        out.print(res[n].multiply(res[n]));
    }
}