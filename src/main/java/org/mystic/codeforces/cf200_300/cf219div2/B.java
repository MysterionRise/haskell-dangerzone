package org.mystic.codeforces.cf200_300.cf219div2;

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

    private int S(long a) {
        return String.valueOf(a).length();
    }

    private long maxInts(int len) {
        if (len == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            sb.append('9');
        }
        return Long.valueOf(sb.toString());
    }

    public void solve() throws IOException {
        final String[] longs = br.readLine().split(" ");
        long w = Long.parseLong(longs[0]);
        long m = Long.parseLong(longs[1]);
        final long k = Long.parseLong(longs[2]);
        final long max[] = new long[18];
        for (int i = 1; i < 18; ++i) {
            max[i] = maxInts(i);
        }
        long ans = 0;
        int len = S(m);
        while (w > 0) {
            final long tmp = max[len] - m + 1L;
            if (w > tmp * len * k) {
                w -= tmp * len * k;
                ans += tmp;
                m = max[len] + 1;
                len++;
            } else {
                final long x = w / (len * k);
                ans += x;
                w = 0;
            }
        }
        out.println(ans);
    }
}
