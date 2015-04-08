package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T407 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T407()).start();
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
        final int[] w = new int[n];
        for (int i = 0; i < n; ++i) {
            w[i] = nextInt();
        }
        final int k = nextInt();
        final int[] dp = new int[k + 1];
        dp[0] = 0;
        for (int i = 1; i <= k; ++i) {
            dp[i] = Integer.MAX_VALUE / 10;
        }
        for (int i = 0; i < n; ++i) {
            for (int c = w[i]; c <= k; ++c) {
                dp[c] = Math.min(dp[c], dp[c - w[i]] + 1);
            }
        }
        if (dp[k] == Integer.MAX_VALUE / 10) {
            out.println(-1);
        } else {
            out.println(dp[k]);
        }
    }
}
