import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "H - Хорошие имена".
 */
public class solver {

    private static int n;
    private static char[][] a;

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    private static boolean dfs(String word, boolean[][] v, int row, int col) {
        if (word.length() == 1) {
            return true;
        }
        v[row][col] = true;
        boolean res = false;
        if ((row - 1 >= 0) && (a[row - 1][col] == word.charAt(1)) && !v[row - 1][col]) {
            res = dfs(word.substring(1), v, row - 1, col);
        }
        if (!res && (row + 1 < n) && (a[row + 1][col] == word.charAt(1)) && !v[row + 1][col]) {
            res = dfs(word.substring(1), v, row + 1, col);
        }
        if (!res && (col - 1 >= 0) && (a[row][col - 1] == word.charAt(1)) && !v[row][col - 1]) {
            res = dfs(word.substring(1), v, row, col - 1);
        }
        if (!res && (col + 1 < n) && (a[row][col + 1] == word.charAt(1)) && !v[row][col + 1]) {
            res = dfs(word.substring(1), v, row, col + 1);
        }

        v[row][col] = false;
        return res;
    }

    static void solve(Scanner in, PrintStream out) {
        String word = in.nextLine();
        n = in.nextInt();
        in.nextLine();
        a = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        boolean res = false;
        boolean[][] v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == word.charAt(0)) {

                    res = dfs(word, v, i, j);
                    if (res) {
                        break;
                    }
                }
            }
            if (res) {
                break;
            }
        }

        out.print(res ? "YES" : "NO");
    }
}