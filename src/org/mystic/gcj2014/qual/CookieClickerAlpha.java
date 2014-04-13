package org.mystic.gcj2014.qual;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Only solve small input
 */
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
            double ans = Double.MAX_VALUE;
            for (int j = 0; j < 20000; ++j) {
                double temp = 0.0d;
                double speed = 2.0d;
                for (int k = 1; k < j; ++k) {
                    temp += C / speed;
                    speed += F;
                }
                temp += X / speed;
                ans = Math.min(ans, temp);
            }
            out.print("Case #" + i + ": ");
            out.printf("%.7f", ans);
            out.println();
        }
    }
}
