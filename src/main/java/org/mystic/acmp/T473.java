package org.mystic.acmp;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class T473 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T473()).start();
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
//            br = new BufferedReader(new FileReader("input.txt"));
//            out = new PrintWriter(new FileWriter("output.txt"));
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        BigInteger prev = BigInteger.ONE;
        BigInteger curr = BigInteger.ONE;
        BigInteger next = BigInteger.ZERO;
        if (n == 1) {
            out.println(1);
            return;
        }
        for (int i = n - 2; i >= 0; --i) {
            next = curr.add(prev);
            prev = curr;
            curr = next;
        }
        out.println(next.multiply(next));
    }
}
