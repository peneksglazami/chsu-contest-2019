import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Решение задачи "E - Код".
 */
public class solver {

    static class Node {
        char value;
        Node child0;
        Node child1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            solve(in, System.out);
        }
    }

    private static void addToTree(String s, Node node) {
        char ch = s.charAt(0);
        if (ch == '0') {
            if (node.child0 == null) {
                node.child0 = new Node();
                node.child0.value = ch;
            }
            if (s.length() > 1) {
                addToTree(s.substring(1), node.child0);
            }
        } else {
            if (node.child1 == null) {
                node.child1 = new Node();
                node.child1.value = ch;
            }
            if (s.length() > 1) {
                addToTree(s.substring(1), node.child1);
            }
        }
    }

    static void solve(Scanner in, PrintStream out) {
        int n = in.nextInt();
        in.nextLine();
        Node root = new Node();
        while (n-- > 0) {
            String s = in.nextLine();
            addToTree(s, root);
        }

        String message = in.nextLine();
        Node current = root;
        int k = 0;
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (ch == '0') {
                current = current.child0;
            } else {
                current = current.child1;
            }
            if ((current.child0 == null) && (current.child1 == null)) {
                k++;
                current = root;
            }
        }

        out.print(k);
    }
}