package org.mystic.codeforces.cf200_300.cf260div2;

import java.io.*;
import java.math.BigInteger;
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
        final BigInteger n = new BigInteger(next());
        int ans = 0;
        int mod = Integer.parseInt(n.mod(BigInteger.valueOf(4L)).toString());
        for (int i = 1; i <= 4; ++i) {
            ans += Math.pow(i, mod);
        }
        out.println(ans % 5);
    }
}
