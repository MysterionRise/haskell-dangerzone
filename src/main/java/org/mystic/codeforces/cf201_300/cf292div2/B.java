package org.mystic.codeforces.cf201_300.cf292div2;

import java.io.*;
import java.util.StringTokenizer;

public class B implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new B()).start();
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
        int m = nextInt();
        int b = nextInt();
        boolean[] boys = new boolean[n];
        for (int i = 0; i < b; ++i) {
            boys[nextInt()] = true;
        }
        int g = nextInt();
        boolean[] girls = new boolean[m];
        for (int i = 0; i < g; ++i) {
            girls[nextInt()] = true;
        }
        for (int i = 0; i <= 10_000_000; ++i) {
            int b1 = i % n;
            int g1 = i % m;
            if (boys[b1] || girls[g1]) {
                boys[b1] = true;
                girls[g1] = true;
            }
        }
        boolean flag = true;
        for (int i = 0; i < n; ++i) {
            flag &= boys[i];
        }
        for (int i = 0; i < m; ++i) {
            flag &= girls[i];
        }
        if (flag) {
            out.println("Yes");
            return;
        }
        out.println("No");
    }
}
