package org.mystic.codeforces.cf203div2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
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
        List<Pair<Long, Long>> bombs = new ArrayList<>();
        List<Pair<Double, Integer>> dist = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            bombs.add(new Pair(nextLong(), nextLong()));
            dist.add(new Pair(bombs.get(i).first * bombs.get(i).first + bombs.get(i).second * bombs.get(i).second, i));
        }
        Collections.sort(dist);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Pair<Long, Long> bomb = bombs.get(dist.get(i).second);
            String roadBack1 = "";
            String roadBack2 = "";
            if (bomb.second != 0) {
                if (bomb.second > 0) {
                    ans.add("1 " + Math.abs(bomb.second) + " U");
                    roadBack2 = "1 " + Math.abs(bomb.second) + " D";
                } else {
                    ans.add("1 " + Math.abs(bomb.second) + " D");
                    roadBack2 = "1 " + Math.abs(bomb.second) + " U";
                }
            }
            if (bomb.first != 0) {
                if (bomb.first > 0) {
                    ans.add("1 " + Math.abs(bomb.first) + " R");
                    roadBack1 = "1 " + Math.abs(bomb.first) + " L";
                } else {
                    ans.add("1 " + Math.abs(bomb.first) + " L");
                    roadBack1 = "1 " + Math.abs(bomb.first) + " R";
                }
            }
            ans.add("2");
            if (roadBack2.length() > 0) {
                ans.add(roadBack2);
            }
            if (roadBack1.length() > 0) {
                ans.add(roadBack1);
            }
            ans.add("3");
        }
        out.println(ans.size());
        for (int i = 0; i < ans.size(); ++i) {
            out.println(ans.get(i));
        }

    }

    class Pair<T extends Comparable, F> implements Comparable {

        T first;
        F second;

        Pair(T first, F second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Object o) {
            Pair pair = (Pair) o;
            return this.first.compareTo(pair.first);
        }
    }
}
