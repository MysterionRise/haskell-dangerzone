package org.mystic.codeforces.cf201div2;

import java.io.*;
import java.util.StringTokenizer;

public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
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
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        int n = nextInt();
        int max = Integer.MIN_VALUE;
        int d = 0;
        for (int i = 0; i < n; ++i) {
            int t = nextInt();
            d = gcd(t, d);
            max = Math.max(max, t);
        }
        out.println(((max / d - n) % 2 == 1) ? "Alice" : "Bob");
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            int t = a;
            a = b;
            b = t;
        }
        return a;
    }


}

