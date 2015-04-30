package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T481 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T481()).start();
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
        final char[] in = next().toCharArray();
        final int N = in.length;
        final int[][] dp = new int[N + 2][N + 2];
        for (int len = 0; len <= N; ++len) {
            for (int i = 1; (i + len) <= N; ++i) {
                int j = i + len;
                if (i == j) {
                    dp[i][j] = 1;
                } else if (Math.abs(i - j) == 1 && in[i - 1] == in[j - 1]) {
                    dp[i][j] = 3;
                } else if (Math.abs(i - j) == 1 && in[i - 1] != in[j - 1]) {
                    dp[i][j] = 2;
                } else if (in[i - 1] == in[j - 1]) {
                    dp[i][j] = 1 + dp[i][j - 1] + dp[i + 1][j];
                } else if (in[i - 1] != in[j - 1]) {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
            }
        }
        out.println(dp[1][N]);
    }
}
