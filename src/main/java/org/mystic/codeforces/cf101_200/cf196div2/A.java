package org.mystic.codeforces.cf101_200.cf196div2;

import java.io.*;
import java.util.Arrays;
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
        int[] p = new int[m];
        for (int i = 0; i < m; ++i) {
            p[i] = nextInt();
        }
        Arrays.sort(p);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= m - n; ++i) {
            min = Math.min(min, Math.abs(p[i] - p[i + n - 1]));
        }
        out.println(min);
    }
}
