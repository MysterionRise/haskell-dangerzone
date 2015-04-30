package org.mystic.codeforces.cf201_300.cf214div2;

import java.io.*;
import java.util.Arrays;
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
        final int n = nextInt();
        final int k = nextInt();
        final int[] a = new int[n];
        final int[] b = new int[n];
        final int[] w = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            b[i] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            w[i] = a[i] - k * b[i];
        }
        final int ZERO = 10000;
        final int len = ZERO * 2;
        final int[][] dp = new int[n + 1][len];
        for (int i = 0; i < n + 1; ++i) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][ZERO] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < len; ++j) {
                if (j - w[i - 1] >= 0 && j - w[i - 1] < dp[i].length) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + a[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        if (dp[n][ZERO] == 0) {
            out.println(-1);
        } else {
            out.println(dp[n][ZERO]);
        }
    }
}
