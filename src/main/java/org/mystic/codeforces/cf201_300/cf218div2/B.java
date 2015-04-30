package org.mystic.codeforces.cf201_300.cf218div2;

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
        int a = nextInt();
        int b = nextInt();
        if (a == b) {
            out.println(0);
            return;
        }
        int[] div = new int[]{2, 3, 5};
        int[] a1 = new int[3];
        int[] b1 = new int[3];
        for (int i = 0; i < div.length; ++i) {
            while (a % div[i] == 0) {
                a /= div[i];
                a1[i]++;
            }
        }
        for (int i = 0; i < div.length; ++i) {
            while (b % div[i] == 0) {
                b /= div[i];
                b1[i]++;
            }
        }
        if (a != b) {
            out.println(-1);
            return;
        }
        int ans = 0;
        for (int i = 0; i < div.length; ++i) {
            ans += Math.abs(a1[i] - b1[i]);
        }
        out.println(ans);
    }
}
