package org.mystic.codeforces.cf200_300.cf208div2;

import java.io.*;
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
        int n = nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append("<3");
        for (int i = 0; i < n; ++i) {
            sb.append(next());
            sb.append("<3");
        }
        char[] words = sb.toString().toCharArray();
        char[] msg = next().toCharArray();
        int pos = 0;
        for (int i = 0; i < msg.length && pos < words.length; ++i) {
            if (msg[i] == words[pos]) {
                ++pos;
            }
        }
        if (pos == words.length) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }
}
