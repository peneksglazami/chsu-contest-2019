import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "A - Цветочки".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int k = in.nextInt();
        String[] res = {"ABC", "CAB", "BCA"};

        out.print(res[k % 3]);
    }
}