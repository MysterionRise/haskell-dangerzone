package org.mystic.codeforces.gym100135;

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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("ladder.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("ladder.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        long[] res = new long[n + 2];
        res[0] = 0;
        res[1] = 0;
        for (int i = 2; i < n + 2; ++i) {
            res[i] = Math.max(res[i - 2] + a[i - 2], res[i - 1] + a[i - 2]);
        }
        out.println(res[n + 1]);
    }

}
