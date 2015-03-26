package org.mystic.custom.qual;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MagicTrick implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new MagicTrick()).start();
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
        int T = nextInt();
        for (int i = 1; i <= T; ++i) {
            int ans1 = nextInt();
            int[][] first = new int[4][4];
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    first[j][k] = nextInt();
                }
            }
            int ans2 = nextInt();
            int[][] second = new int[4][4];
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    second[j][k] = nextInt();
                }
            }
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int j = 0; j < 4; ++j) {
                set1.add(first[ans1 - 1][j]);
                set2.add(second[ans2 - 1][j]);
            }
            set1.retainAll(set2);
            if (set1.size() == 1) {
                out.println("Case #" + i + ": " + set1.iterator().next());
            } else if (set1.isEmpty()) {
                out.println("Case #" + i + ": Volunteer cheated!");
            } else {
                out.println("Case #" + i + ": Bad magician!");
            }
        }
    }
}
