package org.mystic.codeforces.gym100229;

import java.io.*;
import java.util.ArrayList;
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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("topsort.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("topsort.out")));
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
        final int m = nextInt();
        final List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < m; ++i) {
            int a = nextInt();
            int b = nextInt();
            g.get(a - 1).add(b - 1);
        }
        final boolean[] used = new boolean[n];
        final List<Integer> ans = new ArrayList<>();
        final int[] color = new int[n]; //  1 - gray, 2 - black

        class DFSUtils {
            public void dfs(int vertex) {
                used[vertex] = true;
                for (int i = 0; i < g.get(vertex).size(); ++i) {
                    int to = g.get(vertex).get(i);
                    if (!used[to]) {
                        dfs(to);
                    }
                }
                ans.add(vertex);
            }

            public boolean dfs1(int vertex) {
                color[vertex] = 1;
                for (int i = 0; i < g.get(vertex).size(); ++i) {
                    int to = g.get(vertex).get(i);
                    if (color[to] == 0) {
                        if (dfs1(to)) return true;
                    } else if (color[to] == 1) {
                        return true;
                    }
                }
                color[vertex] = 2;
                return false;
            }
        }
        DFSUtils utils = new DFSUtils();

        for (int i = 0; i < n; ++i) {
            if (utils.dfs1(i)) {
                out.println(-1);
                return;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                utils.dfs(i);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            out.println((ans.get(i) + 1) + " ");
        }

    }
}
