package org.mystic.codeforces.cf200_300.cf208div2;


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
        int[] a1 = new int[n];
        for (int i = 0; i < n; ++i) {
            a1[i] = nextInt();
        }
        for (int i = 1; i < n; ++i) {
            int from = Math.min(a1[i - 1], a1[i]);
            int to = Math.max(a1[i - 1], a1[i]);
            for (int j = i; j < n - 1; ++j) {
                int pFrom = Math.min(a1[j], a1[j + 1]);
                int pTo = Math.max(a1[j], a1[j + 1]);
                if (pTo <= from && pFrom <= from || pTo >= to && pFrom >= to || pFrom >= from && pTo <= to || from >= pFrom && to <= pTo) {
                } else {
                    out.println("yes");
                    return;
                }
            }
        }
        out.println("no");

    }

}
