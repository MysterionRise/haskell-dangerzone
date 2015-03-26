package org.mystic.codeforces.cf200_300.cf291div2;

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
        long x = nextLong();
        char[] number = String.valueOf(x).toCharArray();
        int len = number.length;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            if (i == 0 && number[i] == '9') {
                res.append('9');
            } else if (number[i] - 48 >= 5) {
                res.append(9 - (number[i] - '0'));
            } else {
                res.append(number[i] - '0');
            }
        }
        out.println(res.toString());
    }
}
