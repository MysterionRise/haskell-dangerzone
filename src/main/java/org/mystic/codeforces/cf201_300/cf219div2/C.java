package org.mystic.codeforces.cf201_300.cf219div2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
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
        final int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        int ans = 0;
        Arrays.sort(a);
        int index = a.length - 1;
        int j = a.length / 2 - 1;
        boolean[] used = new boolean[n];
        boolean working = true;
        while (working) {
            if (a[index] >= a[j] * 2 && !used[index] && !used[j]) {
                used[index] = true;
                used[j] = true;
                index--;
                j--;
                ans++;
            } else {
                j--;
            }
            if (j == -1) {
                working = false;
            }
        }
        out.println(n - ans);
    }
}
