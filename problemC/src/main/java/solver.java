import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Решение задачи "C - Покер".
 */
public class solver {

    private static int n;
    private static int m;
    private static int s;
    private static int t;
    private static int[][] c;
    private static int[][] f;
    private static int[] d;

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
            // solveGreedy(in, System.out); // Корректное решение жадным алгоритмом
        }
    }

    private static boolean bfs() {
        int[] q = new int[n + m + 2];
        int qh = 0, qt = 0;
        q[qt++] = s;
        Arrays.fill(d, -1);
        d[s] = 0;
        while (qh < qt) {
            int v = q[qh++];
            for (int to = 0; to <= n + m + 1; to++)
                if ((d[to] == -1) && (f[v][to] < c[v][to])) {
                    q[qt++] = to;
                    d[to] = d[v] + 1;
                }
        }
        return d[t] != -1;
    }

    private static int dfs(int v, int flow) {
        if (flow == 0) {
            return 0;
        }
        if (v == t) {
            return flow;
        }
        for (int to = 0; to <= n + m + 1; to++) {
            if (d[to] != d[v] + 1) {
                continue;
            }
            int pushed = dfs(to, Math.min(flow, c[v][to] - f[v][to]));
            if (pushed > 0) {
                f[v][to] += pushed;
                f[to][v] -= pushed;
                return pushed;
            }
        }
        return 0;
    }

    private static int findMaxFlow() {
        int maxFlow = 0;
        while (bfs()) {
            int flow;
            do {
                flow = dfs(s, Integer.MAX_VALUE);
                maxFlow += flow;
            } while (flow > 0);
        }
        return maxFlow;
    }

    static void solve(Scanner in, PrintStream out) {
        n = in.nextInt();
        m = in.nextInt();
        c = new int[n + m + 2][n + m + 2];
        f = new int[n + m + 2][n + m + 2];
        d = new int[n + m + 2];

        int playerAmount = 0;
        for (int i = 1; i <= n; i++) {
            c[0][i] = in.nextInt();
            playerAmount += c[0][i];
        }
        for (int i = 1; i <= m; i++) {
            c[n + i][n + m + 1] = in.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                c[i][n + j] = 1;
            }
        }

        s = 0;
        t = n + m + 1;
        int maxFlow = findMaxFlow();

        out.print((maxFlow == playerAmount) ? "YES" : "NO");
    }


    static void solveGreedy(Scanner in, PrintStream out) {
        n = in.nextInt();
        m = in.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = in.nextInt();
        }

        int[] s = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = in.nextInt();
        }

        Arrays.sort(p);
        boolean ok = true;
        for (int i = n - 1; i >= 0; i--) {
            Arrays.sort(s);
            for (int j = m - 1; j >= 0; j--) {
                if (p[i] == 0) {
                    break;
                }
                if (s[j] > 0) {
                    s[j]--;
                    p[i]--;
                }
            }
            if (p[i] > 0) {
                ok = false;
                break;
            }
        }

        out.print(ok ? "YES" : "NO");
    }

}