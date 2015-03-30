package org.mystic.acmp;

import java.io.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class T109 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T109()).start();
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public void run() {
        try {
//            br = new BufferedReader(new FileReader("input.txt"));
//            out = new PrintWriter(new FileWriter("output.txt"));
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final String[] num = next().split("/");
        int a = Integer.parseInt(num[0]);
        int b = Integer.parseInt(num[1]);
        int part1 = a / b;
        out.print(part1);
        a -= b * part1;
        if (a > 0) {
            out.print(".");
            divide(a, b);
        }
    }

    private void divide(int a, int b) {
        StringBuilder current = new StringBuilder();
        while (current.length() != 10000) {
            a *= 10;
            int divider = a / b;
            int remainder = a % b;
            if (remainder == 0) {
                out.print(current.toString() + divider);
                return;
            }
            current.append(divider);
            a = remainder;
        }
        if (current.length() == 10000) {
            for (int j = 1; j <= 1000; ++j) {
                for (int i = 0; i <= 1000; ++i) {
                    String begin = current.substring(0, i);
                    String part1 = current.substring(i, i + j);
                    String part2 = current.substring(i + j, i + 2 * j);
                    if (part1.equals(part2) && checkAllPeriods(current, part1, i)) {
                        if (!begin.isEmpty()) {
                            out.print(begin);
                        }
                        out.print("(" + part1 + ")");
                        return;
                    }
                }
            }
        }
    }

    private boolean checkAllPeriods(StringBuilder a, String b, int pos) {
        int c = pos;
        final int length = b.length();
        while (c != -1) {
            int next = a.indexOf(b, c + 1);
            if (next != -1 && next - c != length) {
                return false;
            }
            c = next;
        }
        return true;
    }
}
