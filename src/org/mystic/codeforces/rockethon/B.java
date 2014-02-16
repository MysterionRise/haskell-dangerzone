package org.mystic.codeforces.rockethon;


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
        String word = next();
        final int n = word.length();
        int ans = 0;
        for (char c = 'A'; c <= 'Z'; ++c) {
            int tmp = 0;
            int left = 0;
            for (int i = 1; i < n && left < n; ++i) {
                if (i > left) {
                    StringBuffer b = new StringBuffer(word.substring(left, i));
                    char[] begin = b.reverse().toString().toCharArray();
                    char[] end = word.substring(i).toCharArray();
                    for (int j = 0; j < Math.min(begin.length, end.length); ++j) {
                        if (begin[j] == end[j] && begin[j] == c) {
                            tmp++;
                            left = i + j;
                        }
                    }
                }
            }
            ans = Math.max(ans, tmp + 1);
        }
        out.println(ans);
    }


}
