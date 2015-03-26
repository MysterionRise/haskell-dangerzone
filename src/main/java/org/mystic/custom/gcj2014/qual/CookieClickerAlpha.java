package org.mystic.custom.qual.gcj2014;

import java.io.*;
import java.util.StringTokenizer;

public class CookieClickerAlpha implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new CookieClickerAlpha()).start();
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
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
        int T = nextInt();
        for (int i = 1; i <= T; ++i) {
            double C = nextDouble();
            double F = nextDouble();
            double X = nextDouble();
            double ans = X / 2;
            double[] memoTemp = new double[500000];
            double[] memoSpeed = new double[500000];
            memoTemp[0] = 0.0d;
            memoSpeed[0] = 2.0d;
            for (int j = 1; j < 500000; ++j) {
                memoTemp[j] = memoTemp[j - 1] + C / memoSpeed[j - 1];
                memoSpeed[j] = memoSpeed[j - 1] + F;
                ans = Math.min(ans, memoTemp[j] + X / memoSpeed[j]);
            }
            out.print("Case #" + i + ": ");
            out.printf("%.7f", ans);
            out.println();
        }
    }
}
