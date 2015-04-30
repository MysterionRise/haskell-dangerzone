package org.mystic.codeforces.cf201_300.cf289div2;

import java.io.*;
import java.util.StringTokenizer;

public class A implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A()).start();
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
        int[][] a = new int[n][n];
        for (int i = 0; i < n; ++i) {
            a[i][0] = 1;
            a[0][i] = 1;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                a[i][j] = a[i - 1][j] + a[i][j - 1];
            }
        }
        out.println(a[n - 1][n - 1]);
    }
}
