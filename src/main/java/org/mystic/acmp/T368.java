package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T368 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T368()).start();
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
        final int[][] arr = new int[n][n];
        for (int i = 0; i < n; ++i) {
            final char[] ch = next().toCharArray();
            for (int j = 0; j < n; ++j) {
                arr[i][j] = ch[j] - '0';
            }
        }
        final int[][] dp = new int[n][n];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; ++i) {
            dp[0][i] += arr[0][i] + dp[0][i - 1];
            dp[i][0] += arr[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        char[][] ans = new char[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                ans[i][j] = '.';
        }
        int x = n - 1;
        int y = n - 1;
        while (x != 0 || y != 0) {
            ans[x][y] = '#';
            int xs = Integer.MAX_VALUE;
            int ys = Integer.MAX_VALUE;
            if (x > 0) {
                xs = dp[x - 1][y];
            }
            if (y > 0) {
                ys = dp[x][y - 1];
            }
            if (xs < ys) {
                x--;
            } else {
                y--;
            }
        }
        ans[0][0] = '#';
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                out.print(ans[i][j]);
            out.println();
        }
    }
}
