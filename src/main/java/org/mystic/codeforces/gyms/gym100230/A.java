package org.mystic.codeforces.gyms.gym100230;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A()).start();
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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("dijkstra.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("dijkstra.out")));
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
        int s = nextInt() - 1;
        int f = nextInt() - 1;
        final List<List<Edge>> g = new ArrayList<>();
        final int[] dist = new int[n];
        final boolean[] used = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<Edge>());
            for (int j = 0; j < n; ++j) {
                int a = nextInt();
                if (i != j && a != -1) {
                    g.get(i).add(new Edge(j, a));
                }
            }
        }
        class Dijkstra {
            void findPath(int s) {
                dist[s] = 0;
                for (int i = 0; i < n; ++i) {
                    int v = -1;
                    for (int j = 0; j < n; ++j) {
                        if (!used[j] && (v == -1 || dist[j] < dist[v])) {
                            v = j;
                        }
                    }
                    if (dist[v] == Integer.MAX_VALUE)
                        break;
                    used[v] = true;
                    for (int j = 0; j < g.get(v).size(); ++j) {
                        int to = g.get(v).get(j).to;
                        int len = g.get(v).get(j).len;
                        if (dist[v] + len < dist[to]) {
                            dist[to] = dist[v] + len;
                        }
                    }
                }
            }

        }

        Dijkstra d = new Dijkstra();
        d.findPath(s);
        out.println(dist[f] == Integer.MAX_VALUE ? -1 : dist[f]);
    }

    class Edge {
        int to;
        int len;

        Edge(int to, int len) {
            this.to = to;
            this.len = len;
        }
    }
}
