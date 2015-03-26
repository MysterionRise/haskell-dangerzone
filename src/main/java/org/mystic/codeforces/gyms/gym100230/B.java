package org.mystic.codeforces.gyms.gym100230;


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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("distance.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("distance.out")));
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
        int m = nextInt();
        int s = nextInt() - 1;
        int f = nextInt() - 1;
        final List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < m; ++i) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            int w = nextInt();
            g.get(a).add(new Edge(b, w));
            g.get(b).add(new Edge(a, w));
        }
        final int[] dist = new int[n];
        final int[] prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        class Dijkstra {
            void findPath(int s) {
                dist[s] = 0;
                PriorityQueue<Edge> q = new PriorityQueue<>();
                q.add(new Edge(s, 0));
                while (!q.isEmpty()) {
                    int v = q.peek().to;
                    int currD = q.peek().len;
                    q.poll();
                    if (currD > dist[v]) continue;
                    for (int j = 0; j < g.get(v).size(); ++j) {
                        int to = g.get(v).get(j).to;
                        int len = g.get(v).get(j).len;
                        if (dist[v] + len < dist[to]) {
                            dist[to] = dist[v] + len;
                            prev[to] = v;
                            q.add(new Edge(to, dist[to]));
                        }
                    }
                }
            }

        }

        Dijkstra d = new Dijkstra();
        d.findPath(s);
        if (dist[f] == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(dist[f]);
            ArrayList<Integer> path = new ArrayList<>();
            for (int v = f; v != s; v = prev[v]) {
                path.add(v);
            }
            path.add(s);
            for (int i = path.size() - 1; i >= 0; --i) {
                out.print((path.get(i) + 1) + " ");
            }
        }
    }

    class Edge implements Comparable<Edge> {
        int to;
        int len;

        Edge(int to, int len) {
            this.to = to;
            this.len = len;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.len, o.len);
        }
    }
}
