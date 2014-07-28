package org.mystic.codeforces.cf197div2;

import java.io.*;
import java.util.Arrays;
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
        String str = next();
        String[] split = str.split("\\+");
        int[] spl = new int[split.length];
        for (int i = 0; i < spl.length; ++i) {
            spl[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(spl);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spl.length; ++i) {
            sb.append(spl[i]).append("+");
        }
        sb.deleteCharAt(sb.length() - 1);
        out.println(sb.toString());

    }
}
