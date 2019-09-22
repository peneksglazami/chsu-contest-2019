import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

/**
 * Решение задачи "G - Виноградник".
 */
public class solver {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    static class Point {
        int x;
        int y;
    }

    private static int vect(Point a1, Point a2, Point b1, Point b2) {
        return (a2.x - a1.x) * (b2.y - b1.y) - (b2.x - b1.x) * (a2.y - a1.y);
    }

    private static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();

        if (n > 1) {
            Point[] a = new Point[n];

            for (int i = 0; i < n; i++) {
                a[i] = new Point();
                a[i].x = in.nextInt();
                a[i].y = in.nextInt();
            }

            int min = 0;
            for (int i = 1; i < n; i++) {
                if ((a[min].x > a[i].x)
                        || ((a[min].x == a[i].x) && (a[min].y > a[i].y))) {
                    min = i;
                }
            }

            Point[] b = new Point[n + 1];
            b[0] = a[min];
            a[min] = a[0];
            a[0] = b[0];

            int k = 0;
            min = 1;
            do {
                for (int i = 1; i < n; i++) {
                    if ((vect(b[k], a[min], b[k], a[i]) < 0)
                            || ((vect(b[k], a[min], b[k], a[i]) == 0) && (dist(b[k], a[min]) < dist(b[k], a[i])))) {
                        min = i;
                    }
                }
                k++;
                b[k] = a[min];
                min = 0;
            } while (!((b[k].x == b[0].x) && (b[k].y == b[0].y)));

            double l = 0;
            for (int i = 0; i < k; i++) {
                l += Math.sqrt(dist(b[i], b[i + 1]));
            }
            l += 2 * Math.PI;

            DecimalFormat df = new DecimalFormat("#.##");
            DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            df.setRoundingMode(RoundingMode.HALF_UP);
            out.print(df.format(l));
        } else {
            out.print("6.28");
        }
    }
}