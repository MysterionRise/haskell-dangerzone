package org.mystic.acmp;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class T29 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T29()).start();
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
        final int[] platforms = new int[n];
        for (int i = 0; i < n; ++i) {
            platforms[i] = nextInt();
        }
        if (n == 1) {
            out.println(0);
            return;
        }
        final long[] dp = new long[n];
        dp[1] = Math.abs(platforms[1] - platforms[0]);
        dp[0] = 0;
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.min(dp[i - 1] + Math.abs(platforms[i] - platforms[i - 1]), dp[i - 2] + 3 * Math.abs(platforms[i] - platforms[i - 2]));
        }
        out.println(dp[n - 1]);
    }
}