package org.mystic.codeforces.gyms.gym100135;

import java.io.*;
import java.util.Arrays;
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public void run() {
        try {
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("lepus.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("lepus.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final char[] chars = next().toCharArray();
        long[] res = new long[n + 5];
        for (int i = 5; i < n + 5; ++i) {
            if (chars[i - 5] == 'w') {
                res[i] = -1;
            } else if (chars[i - 5] == '"') {
                res[i] = 1;
            } else {
                res[i] = 0;
            }
        }
        long[] ans = new long[n + 5];
        Arrays.fill(ans, -1);
        ans[5] = 0;
        for (int i = 6; i < n + 5; ++i) {
            ans[i] = Math.max(ans[i - 1], Math.max(ans[i - 5], ans[i - 3])) + res[i];
        }
        out.println(ans[n + 4]);
    }
}
