package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T165 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T165()).start();
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
        final int[][] field = new int[n][m];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {
                field[i][j] = nextInt();
            }
        }
        final int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            final int x = field[i][0];
            if (i - x >= 0)
                dp[i][0] += dp[i - x][0];
        }
        for (int j = 1; j < m; ++j) {
            final int x = field[0][j];
            if (j - x >= 0)
                dp[0][j] += dp[0][j - x];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                final int x = field[i][j];
                if (i - x >= 0)
                    dp[i][j] += dp[i - x][j];
                if (j - x >= 0)
                    dp[i][j] += dp[i][j - x];
            }
        }
        out.println(dp[n - 1][m - 1]);
    }
}