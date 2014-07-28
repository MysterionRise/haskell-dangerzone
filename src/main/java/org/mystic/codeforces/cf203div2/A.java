package org.mystic.codeforces.cf203div2;

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
        int m = nextInt();
        int[] a = new int[n];
        int max = Integer.MIN_VALUE;
        int min_2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            max = Math.max(max, a[i]);
            min_2 = Math.min(min_2, a[i]);
        }
        int min = Integer.MAX_VALUE;
        int[] b = new int[m];
        for (int i = 0; i < m; ++i) {
            b[i] = nextInt();
            min = Math.min(min, b[i]);
        }
        for (int i = 1; i <= 100; ++i) {
            if (i >= min_2 * 2 && i < min && i >= max) {
                out.print(i);
                return;
            }
        }
        out.println(-1);
    }

}
