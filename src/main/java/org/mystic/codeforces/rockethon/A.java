package org.mystic.codeforces.rockethon;


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
        final char[] str = next().toCharArray();
        final int n = str.length;
        int ans = 0;
        for (int i = 0; i < n;) {
            char c = str[i];
            int j = i;
            int len = 0;
            while(j < n && str[j] == c) {
                ++j;
                ++len;
            }
            if (len % 2 == 0) {
                ans++;
            }
            i = j;
        }
        out.println(ans);
    }
}
