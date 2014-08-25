package org.mystic.codeforces.gym100083;

import java.io.*;
import java.util.*;

public class A1 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A1()).start();
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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("ancestor.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("ancestor.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final List<List<Integer>> gr = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            gr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; ++i) {
            int s = nextInt();
            if (s != 0) {
                gr.get(s - 1).add(i);
            }
        }
        final int[][] g = new int[n][];
        for (int i = 0; i < n; ++i) {
            final int size = gr.get(i).size();
            g[i] = new int[size];
            for (int j = 0; j < size; ++j) {
                g[i][j] = gr.get(i).get(j);
            }
        }
        class Helper {

            final boolean[] used = new boolean[n];

            boolean dfs(int from, int to) {
                Arrays.fill(used, false);
                used[from] = true;
                Deque<Integer> q = new LinkedList<>();
                q.addFirst(from);
                while (!q.isEmpty()) {
                    int v = q.pollFirst();
                    if (v == to) {
                        return true;
                    }
                    final int length = g[v].length;
                    for (int i = 0; i < length; ++i) {
                        if (!used[g[v][i]]) {
                            q.addFirst(g[v][i]);
                            used[g[v][i]] = true;
                        }
                    }

                }
                return false;
            }
        }
        Helper h = new Helper();
        int m = nextInt();
        for (int i = 0; i < m; ++i) {
            {
                int a = nextInt() - 1;
                int b = nextInt() - 1;
                if (h.dfs(a, b)) {
                    out.println(1);
                } else {
                    out.println(0);
                }
            }
        }
    }
}
