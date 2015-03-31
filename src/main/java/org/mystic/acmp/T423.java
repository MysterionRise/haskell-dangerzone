package org.mystic.acmp;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class T423 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T423()).start();
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
        final String in = next();
        final char[] str = in.toCharArray();
        final int len = in.length();
        BigInteger prev = BigInteger.ONE;
        BigInteger curr = BigInteger.ONE;
        BigInteger next = BigInteger.ZERO;
        if (len == 1) {
            out.println(1);
            return;
        }
        for (int i = len - 2; i >= 0; --i) {
            if (str[i] == '0') {
                next = curr;
            } else if (Integer.parseInt(in.substring(i, i + 2)) > 33) {
                next = curr;
            } else {
                next = curr.add(prev);
            }
            prev = curr;
            curr = next;

        }
        out.println(next);
//        out.println(findAns(in));
    }

    // recursive solution
    private long findAns(String in) {
        final int len = in.length();
        if (len == 0) {
            return 1L;
        }
        if (len == 1) {
            return 1L;
        }
        if (in.startsWith("0")) {
            return findAns(in.substring(1));
        }
        if (Integer.parseInt(in.substring(0, 2)) > 33) {
            return findAns(in.substring(1));
        }
        return findAns(in.substring(1)) + findAns(in.substring(2));
    }
}