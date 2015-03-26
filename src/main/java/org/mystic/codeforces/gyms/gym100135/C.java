package org.mystic.codeforces.gyms.gym100135;

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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("knight.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("knight.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int k = nextInt();
        final long[][] dp = new long[n][k];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < k; ++j) {
                long a = 0, b = 0;
                if (i - 2 >= 0 && j - 1 >= 0) {
                    a = dp[i - 2][j - 1];
                }
                if (i - 1 >= 0 && j - 2 >= 0) {
                    b = dp[i - 1][j - 2];
                }
                dp[i][j] = a + b;
            }
        }
        out.println(dp[n - 1][k - 1]);
    }
}
