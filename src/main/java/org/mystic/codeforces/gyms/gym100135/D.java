package org.mystic.codeforces.gyms.gym100135;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new D()).start();
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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("king2.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("king2.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = 8;
        final int[][] tmp = new int[n][n];
        final int[][] field = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tmp[i][j] = nextInt();
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                field[n - i - 1][j] = tmp[i][j];
            }
        }
        final long[][] dp = new long[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = field[0][0];
        for (int i = 1; i < n; ++i) {
            dp[0][i] = dp[0][i - 1] + field[0][i];
        }
        for (int i = 1; i < n; ++i) {
            dp[i][0] = dp[i - 1][0] + field[i][0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + field[i][j];
            }
        }
        out.println(dp[n - 1][n - 1]);
    }
}
