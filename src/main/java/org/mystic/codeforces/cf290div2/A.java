package org.mystic.codeforces.cf290div2;

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
        boolean right = true;
        for (int i = 1; i <= n; ++i) {
            if (i % 2 == 0) {
                if (right) {
                    for (int j = 0; j < m - 1; ++j) {
                        out.print(".");
                    }
                    out.print("#");
                } else {
                    out.print("#");
                    for (int j = 0; j < m - 1; ++j) {
                        out.print(".");
                    }
                }
                out.println();
                right = !right;
            } else {
                for (int j = 0; j < m; ++j) {
                    out.print("#");
                }
                out.println();
            }
        }
    }
}
