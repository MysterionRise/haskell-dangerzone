package org.mystic.codeforces.cf200_300.cf290div2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        final int n = nextInt();
        final int m = nextInt();
        final char[][] colors = new char[n][m];
        for (int i = 0; i < n; ++i) {
            colors[i] = next().toCharArray();
        }
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n * m; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    for (int l = 0; l < m; ++l) {
                        if (i == k && l == j) {
                        } else if (((k == i && Math.abs(l - j) == 1) || (l == j && Math.abs(k - i) == 1)) && colors[i][j] == colors[k][l]) {
                            graph.get(i * m + j).add(k * m + l);
                        }
                    }
                }
            }
        }

        class Utils {
            int[] color = new int[n * m];
            int[] prev = new int[n * m];

            public int dfs(int start, int v, int len) {
                color[v] = 1;
                for (int i = 0; i < graph.get(v).size(); ++i) {
                    int to = graph.get(v).get(i);
                    if (prev[v] != to) {
                        if (color[to] == 0) {
                            prev[to] = v;
                            final int length = dfs(start, to, len + 1);
                            if (length != 0) {
                                return length;
                            }
                        }
                        if (color[to] == 1 && len >= 4 && to == start) {
                            return len;
                        }
                    }
                }
                color[v] = 2;
                return 0;
            }
        }

        Utils u = new Utils();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                Arrays.fill(u.color, 0);
                Arrays.fill(u.prev, -1);
                final int len = u.dfs(i * m + j, i * m + j, 1);
                if (len >= 4) {
                    out.println("Yes");
                    return;
                }
            }
        }
        out.println("No");
    }
}
