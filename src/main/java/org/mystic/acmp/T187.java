package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T187 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T187()).start();
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
        final int sz = 2 * n - 1;
        final long[][] dp = new long[sz][sz];
        dp[0][0] = 1L;
        for (int i = 0; i < n; ++i) {
            dp[0][i] = 1L;
            dp[i][0] = 1L;
        }
        for (int i = 1; i < sz; ++i) {
            for (int j = getStart(i, n); j < getFinsh(i, n, sz); ++j) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1];
            }
        }
        out.println(dp[sz - 1][sz - 1]);
    }

    private int getFinsh(int i, int n, int sz) {
        if (i < n)
            return i + n;
        return sz;
    }

    private int getStart(int i, int n) {
        if (i < n)
            return 1;
        return i - n + 1;
    }
}