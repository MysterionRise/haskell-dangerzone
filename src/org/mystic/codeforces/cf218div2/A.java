package org.mystic.codeforces.cf218div2;


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
        int k = nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        int[] b = new int[k];
        for (int i = 0; i < k; ++i) {
            b[i] = a[i];
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] == b[i % k]) {
                ++cnt;
            }
        }
        if (cnt == n) {
            out.println(0);
            return;
        }

        int[] num = new int[k];
        for (int i = 0; i < n; ++i) {
            if (a[i] == 1) {
                num[i % k]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < k; ++i) {
            ans += Math.min(num[i], n / k - num[i]);
        }
        out.println(ans);
    }


}
