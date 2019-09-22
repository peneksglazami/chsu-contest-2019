import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "B - Одежда".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        int[] a = new int[101];
        while (n-- > 0) {
            int x = in.nextInt();
            a[x]++;
        }

        int max = 0;
        for (int i = 1; i <= 100; i++) {
            if (a[i] > a[max]) {
                max = i;
            }
        }
        int cnt = 0;
        for (int i = 1; i <= 100; i++) {
            if (a[i] == a[max]) {
                cnt++;
            }
        }

        out.print((cnt == 1) ? max : 0);
    }
}