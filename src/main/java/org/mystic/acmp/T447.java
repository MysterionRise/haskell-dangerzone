package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T447 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T447()).start();
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
        final int N = nextInt();
        long res = 1;
        for (int i = 2; i <= N; ++i) {
            res *= i;
            res = findLastNonZeroDigits(res);
        }
        out.println(findLastDigit(res));
    }

    private int findLastDigit(long res) {
        final char[] s = String.valueOf(res).toCharArray();
        for (int i = s.length - 1; i >= 0; --i) {
            if (s[i] != '0') {
                return s[i] - '0';
            }
        }
        return '0';
    }

    private long findLastNonZeroDigits(long res) {
        final char[] s = String.valueOf(res).toCharArray();
        StringBuilder sb = new StringBuilder();
        int ind = s.length - 1;
        for (int i = s.length - 1; i >= 0; --i) {
            if (s[i] != '0') {
                ind = i;
                break;
            }
        }
        int cnt = 0;
        for (int i = ind; i >= 0 && cnt < 10; --i, cnt++) {
            sb.append(s[i]);
        }
        return Long.parseLong(sb.reverse().toString());
    }
}
