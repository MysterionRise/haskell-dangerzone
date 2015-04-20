package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T225 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T225()).start();
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
        final int[] a = new int[n];
        final int[] b = new int[n];
        final int[] c = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            b[i] = nextInt();
            c[i] = nextInt();
        }
        final long[] dp = new long[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                dp[i] = a[i];
            } else if (i == 1) {
                dp[i] = Math.min(dp[i - 1] + a[i], b[i - 1]);
            } else if (i == 2) {
                dp[i] = Math.min(dp[i - 1] + a[i], Math.min(dp[i - 2] + b[i - 1], c[i - 2]));
            } else {
                dp[i] = Math.min(dp[i - 1] + a[i], Math.min(dp[i - 2] + b[i - 1], dp[i - 3] + c[i - 2]));
            }
        }
        out.println(dp[n - 1]);
    }
}
