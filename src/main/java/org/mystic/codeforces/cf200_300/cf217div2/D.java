package org.mystic.codeforces.cf200_300.cf217div2;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

// @todo
public class D implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new D()).start();
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
        final int m = nextInt();
        TreeSet<Integer> Xs = new TreeSet<>();
        TreeSet<Integer> Ys = new TreeSet<>();
        char[][] monitor = new char[n][m];
        for (int i = 0; i < n; ++i) {
            monitor[i] = next().toCharArray();
            for (int j = 0; j < m; ++j) {
                if (monitor[i][j] == 'w') {
                    Xs.add(i);
                    Ys.add(j);
                }
            }
        }
        int dim = Math.max(Xs.last() - Xs.first(), Ys.last() - Ys.first());
        if (Xs.last() - Xs.first() != dim) {

        } else if (Ys.last() - Ys.first() != dim) {

        } else {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (monitor[i][j] == 'w' && i != Xs.first() && i != Xs.last() && j != Ys.first() && j != Ys.last()) {
                        out.println(-1);
                        return;
                    }
                }
            }
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (monitor[i][j] != 'w' && (i == Xs.first() || i == Xs.last()) || (j == Ys.first() || j == Ys.last())) {
                        monitor[i][j] = '+';
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                out.print(monitor[i][j]);
            }
            out.println();
        }
    }
}
