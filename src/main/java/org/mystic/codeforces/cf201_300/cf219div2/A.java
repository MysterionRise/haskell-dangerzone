package org.mystic.codeforces.cf201_300.cf219div2;


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
        final int k = nextInt();
        int[] a = new int[10];
        for (int i = 0; i < 4; ++i) {
            char[] c = next().toCharArray();
            for (int j = 0; j < 4; ++j) {
                if (c[j] != '.') {
                    a[c[j] - '0']++;
                }
            }
        }
        for (int anA : a) {
            if (anA > k * 2) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");

    }
}
