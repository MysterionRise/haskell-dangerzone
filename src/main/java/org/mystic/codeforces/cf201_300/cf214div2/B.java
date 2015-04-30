package org.mystic.codeforces.cf201_300.cf214div2;

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
        final int n = nextInt();
        final int k = nextInt();
        final int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        int[] ans = new int[k];
        int num = 0;
        for (int i = 0; i < k; ++i) {
            for (int j = i; j < n; j += k) {
                ans[num] += a[j];
            }
            num++;
        }
        int min = Integer.MAX_VALUE;
        int number = -1;
        for (int i = 0; i < ans.length; ++i) {
            if (ans[i] < min) {
                min = ans[i];
                number = i;
            }
        }
        out.println(number + 1);
    }
}
