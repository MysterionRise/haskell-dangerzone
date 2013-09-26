package org.mystic.codeforces.gym100229;

import java.io.*;
import java.util.StringTokenizer;

public class O implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new O()).start();
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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("road.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("road.out")));
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int m = 2;
        String[] arr = new String[m];
        for (int i = 0; i < m; ++i) {
            arr[i] = next();
        }
        final int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
        final int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        final int[][] g = new int[m * n][m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i].charAt(j) == '.') {
                    for (int k = 0; k < 8; ++k) {
                        int x1 = i + dx[k];
                        int y1 = j + dy[k];
                        if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && arr[x1].charAt(y1) == '.') {
                            g[convert(x1, y1, n)][convert(i, j, n)] = 1;
                            g[convert(i, j, n)][convert(x1, y1, n)] = 1;
                        }
                    }
                }
            }
        }
        final boolean[] used = new boolean[n * m];
        final int len = n * m;
        final int dest = n - 1;

        class DFSUtils {
            public boolean dfs(int v) {
                if (v == dest) {
                    return true;
                }
                used[v] = true;
                for (int i = 0; i < len; ++i) {
                    if (g[v][i] == 1 && !used[i]) {
                        return dfs(i);
                    }
                }
                return false;
            }
        }

        DFSUtils utils = new DFSUtils();
        if (utils.dfs(0)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    private int convert(int x1, int y1, int n) {
        return x1 * n + y1;
    }
}
