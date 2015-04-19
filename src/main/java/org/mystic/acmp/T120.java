package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T120 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T120()).start();
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
        final int m = nextInt();
        final int[][] a = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                a[i][j] = nextInt();
            }
        }
        final int[][] dp = new int[n][m];
        dp[0][0] = a[0][0];
        int sum = dp[0][0];
        for (int i = 1; i < m; ++i) {
            dp[0][i] = a[0][i] + sum;
            sum = dp[0][i];
        }
        sum = dp[0][0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = a[i][0] + sum;
            sum = dp[i][0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]) + a[i][j];
            }
        }
        out.println(dp[n - 1][m - 1]);
    }
}