import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "I - Дороги".
 * <p>
 * Алгоритм решения:
 * 1. Находим все ребра, являющиеся мостами в графе.
 * 2. Если найдено хотя бы один мост, то ответ NO, в противном случае YES.
 */
public class solver {

    private static boolean[][] a;
    private static boolean[] v;
    private static int[] time;
    private static int[] f;
    private static boolean hasBridge;
    private static int n;
    private static int timer;

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    private static void dfs(int current, int parent) {
        v[current] = true;
        time[current] = f[current] = timer++;
        for (int to = 0; to < n; to++) {
            if (a[current][to] && (to != parent)) {
                if (v[to]) {
                    f[current] = Math.min(f[current], time[to]);
                } else {
                    dfs(to, current);
                    f[current] = Math.min(f[current], f[to]);
                    if (f[to] > time[current]) {
                        hasBridge = true;
                    }
                }
            }
        }
    }

    static void solve(Scanner in, PrintStream out) {
        n = in.nextInt();
        int m = in.nextInt();
        a = new boolean[n][n];
        v = new boolean[n];
        time = new int[n];
        f = new int[n];

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            a[x][y] = a[y][x] = true;
        }

        hasBridge = false;
        timer = 0;
        dfs(0, -1);

        out.print(hasBridge ? "NO" : "YES");
    }
}