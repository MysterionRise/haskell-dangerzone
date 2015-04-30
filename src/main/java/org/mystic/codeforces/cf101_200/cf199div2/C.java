package org.mystic.codeforces.cf101_200.cf199div2;

import java.io.*;
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
        int r = nextInt();
        int h = nextInt();
        int ans = (h / r) * 2;
        h -= (h / r) * r;
        if (2 * h >= r) {
            ans += 2;
        } else {
            ans++;
        }
        if (3.0d * r * r <= 4.0d * h * h) {
            ans++;
        }
        out.println(ans);
    }
}
