package org.mystic.acmp;

import java.io.*;
import java.util.*;

public class T414 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T414()).start();
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
//            br = new BufferedReader(new FileReader("input.txt"));
//            out = new PrintWriter(new FileWriter("output.txt"));
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
        final int a = nextInt();
        final int b = nextInt();
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int i = 2; i <= n; ++i) {
            int j = nextInt();
            graph.get(j).add(i);
        }
        class Utils {

            final boolean[] used = new boolean[n + 1];
            final int[] prev = new int[n + 1];

            public void dfs(int v) {
                used[v] = true;
                for (int i = 0; i < graph.get(v).size(); ++i) {
                    final int w = graph.get(v).get(i);
                    if (!used[w]) {
                        prev[w] = v;
                        dfs(w);
                    }
                }
            }

            public void cleanUp() {
                Arrays.fill(used, false);
                Arrays.fill(prev, -1);
            }
        }
        Utils u = new Utils();
        u.cleanUp();
        u.dfs(1);
        int a1 = a;
        final Set<Integer> aPath = new HashSet<>();
        while (a1 != 1) {
            aPath.add(a1);
            a1 = u.prev[a1];
        }
        aPath.add(1);
        int ans = b;
        while (!aPath.contains(ans)) {
            ans = u.prev[ans];
        }
        out.println(ans);
    }
}
