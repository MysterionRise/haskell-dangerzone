package org.mystic.codeforces.cf196div2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author kperikov
 */
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
        int x = nextInt();
        int y = nextInt();
        int a = nextInt();
        int b = nextInt();
        int up = -1;
        int down = -1;
        if (a * y - x * b > 0) {
            up = a * y - x * b;
            down = a * y;
        } else {
            up = x * b - a * y;
            down = x * b;
        }
        int gcd = gcd(up, down);
        up /= gcd;
        down /= gcd;
        out.println(up + "/" + down);

    }

    private int gcd(int up, int down) {
        if (down == 0)
            return up;
        else
            return gcd(down, up % down);
    }


}
