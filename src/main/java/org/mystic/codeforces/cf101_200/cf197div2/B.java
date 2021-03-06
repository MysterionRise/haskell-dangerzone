package org.mystic.codeforces.cf101_200.cf197div2;

import java.io.*;
import java.util.StringTokenizer;

public class B implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new B()).start();
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
        int[] b = new int[m];
        for (int i = 0; i < m; ++i) {
            b[i] = nextInt();
        }
        long ans = 0;
        int pos = 1;
        for (int i = 0; i < m; ++i) {
            if (pos < b[i]) {
                ans += b[i] - pos;
                pos = b[i];
            } else if (pos > b[i]) {
                ans += b[i] + n - pos;
                pos = b[i];
            }
        }
        out.println(ans);
    }
}
