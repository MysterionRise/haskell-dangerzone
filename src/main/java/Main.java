import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public static void main(String[] a) throws Exception {
        char[] t = new Scanner(new File("input.txt")).next().toCharArray();
        int i = t.length - 1, l = t[i], h;
        BigInteger p = BigInteger.ONE, c = p, n = p;
        PrintWriter o = new PrintWriter("output.txt");
        while (i-- > 0) {
            n = c;
            h = t[i];
            if (h != 48 && h * 100 + l < 5152)
                n = c.add(p);
            p = c;
            c = n;
            l = h;
        }
        o.print(n);
        o.close();
    }
}