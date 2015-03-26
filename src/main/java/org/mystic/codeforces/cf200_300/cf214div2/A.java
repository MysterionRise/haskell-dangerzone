package org.mystic.codeforces.cf200_300.cf214div2;


import java.io.*;
import java.util.StringTokenizer;

public class A implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A()).start();
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
        int n = nextInt();
        int minSum = Integer.MAX_VALUE;
        int ans = -1;
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < 4; ++i) {
            int min1 = Math.min(nextInt(), nextInt());
            int min2 = Math.min(nextInt(), nextInt());
            if (min1 + min2 < minSum) {
                minSum = min1 + min2;
                ans = i + 1;
                ans1 = min1;
                ans2 = min2;
            }
        }
        if (minSum <= n) {
            out.print(ans + " " + ans1 + " " + (n - ans1));
        } else {
            out.println(-1);
        }
    }

}
