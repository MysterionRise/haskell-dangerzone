package org.mystic.codeforces.cf217div2;

import java.io.*;
import java.util.*;

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
        final int n = nextInt();
        final int[] m = new int[n];
        List<Set<Integer>> players = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            m[i] = nextInt();
            players.add(new HashSet<Integer>());
            for (int j = 0; j < m[i]; ++j) {
                players.get(i).add(nextInt());
            }
        }
        boolean[] ans = new boolean[n];
        for (int i = 0; i < n; ++i) {
            final Set<Integer> check = players.get(i);
            boolean isOk = true;
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    Set<Integer> diff1 = new HashSet<>(check);
                    Set<Integer> diff2 = new HashSet<>(players.get(j));
                    diff1.removeAll(players.get(j));
                    diff2.removeAll(players.get(i));
                    if (!diff1.isEmpty() && diff2.isEmpty() || (diff1.isEmpty() && diff2.isEmpty())) {
                        isOk = false;
                    }
                }
            }
            ans[i] = isOk;
        }
        for (int i = 0; i < n; ++i) {
            if (ans[i]) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
