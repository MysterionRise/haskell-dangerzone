package org.mystic.codeforces.custom.rockethon.year2014;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class C1 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C1()).start();
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
        final int n = nextInt();
        final int k = nextInt();
        final int[] score = new int[n];
        final int[] powers = new int[n];
        for (int i = 0; i < n; ++i) {
            score[i] = nextInt();
            powers[i] = nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= Math.pow(2, n); ++i) {
            int tmp = 0;
            int manaoScore = 0;
            final char[] chars = parse(i, n);
            ArrayList<Fighter> s = new ArrayList<>();
            for (int j = 0; j < chars.length; ++j) {
                if (chars[j] == '1') {
                    tmp += powers[j];
                    s.add(new Fighter(j, score[j]));
                    manaoScore++;
                } else {
                    s.add(new Fighter(j, score[j] + 1));
                }

            }
            Collections.sort(s);
            int pos = n + 1;
            for (int j = 0; j < chars.length; ++j) {
                if (manaoScore == s.get(j).score) {
                    if (chars[s.get(j).id] == '1') {
                        pos--;
                    }
                }
                if (manaoScore > s.get(j).score) {
                    pos--;
                }
            }
            if (pos <= k) {
                ans = Math.min(ans, tmp);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }

    private char[] parse(int i, int n) {
        char[] res = new char[n];
        String s = Integer.toBinaryString(i);
        int j;
        for (j = 0; j < n - s.length(); ++j) {
            res[j] = '0';
        }
        for (int k = 0; j < n && k < s.length(); ++j, ++k) {
            res[j] = s.charAt(k);
        }
        return res;
    }

    class Fighter implements Comparable<Fighter> {
        int id;
        int score;

        Fighter(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Fighter o) {
            return Integer.compare(score, o.score);
        }
    }

}
