package org.mystic.codeforces.cf217div2;


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
        int r1 = nextInt();
        int c1 = nextInt();
        int r2 = nextInt();
        int c2 = nextInt();
        if (r1 == r2 || c1 == c2) {
            out.print(1 + " ");
        } else {
            out.print(2 + " ");
        }
        if (r1 + c1 == r2 + c2 || Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
            out.print(1 + " ");
        } else if ((r1 + c1) % 2 == (r2 + c2) % 2) {
            out.print(2 + " ");
        } else {
            out.print(0 + " ");
        }
        out.println(Math.max(Math.abs(r1 - r2),                             Math.abs(c1 - c2)));
    }

}
