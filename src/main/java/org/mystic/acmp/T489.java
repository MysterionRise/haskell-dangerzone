package org.mystic.acmp;

import java.io.*;
import java.util.*;

public class T489 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T489()).start();
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
        final int n = nextInt();
        final int k = nextInt();
        int sum = 0;
        int diff = 0;
        for (int i = 0; i < n; ++i) {
            final String next = next();
            int a;
            if (next.charAt(0) == '–') {
                diff++;
                a = Integer.parseInt(next.substring(1));
            } else {
                a = Integer.parseInt(next);
            }
            sum += Math.abs(a);
        }
        for (int i = 0; i < n - 1; ++i) {
            final String next = next();
            int a;
            if (next.charAt(0) == '–') {
                diff++;
                a = Integer.parseInt(next.substring(1));
            } else {
                a = Integer.parseInt(next);
            }
            sum -= Math.abs(a);
        }
        diff += k;
        if (diff % 2 == 1) {
            out.print("-");
        }
        out.println(sum);
    }
}
