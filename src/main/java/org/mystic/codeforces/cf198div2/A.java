package org.mystic.codeforces.cf198div2;

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
        int x = nextInt();
        int y = nextInt();
        int a = nextInt();
        int b = nextInt();
        int lcm = (x * y) / gcd(x, y);
        out.println(lcm);
        int t1 = a;
        int t2 = b;
        while (t1 % lcm != 0) {
            t1++;
        }
        while (t2 % lcm != 0) {
            t2--;
        }
        out.println((t2 - t1) / lcm + 1);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            int t = a;
            a = b;
            b = t;
        }
        return a;
    }
}
