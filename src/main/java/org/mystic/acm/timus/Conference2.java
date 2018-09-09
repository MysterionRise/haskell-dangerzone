package org.mystic.acm.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Conference2 {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public Conference2() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new Conference2().solve();
    }

    int[] readInts() throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    int readInt() throws IOException {
        if (tt == null || tx == tt.length) {
            tt = readInts();
            tx = 0;
        }
        return tt[tx++];
    }

    private void solve() throws IOException {
        int m = readInt();
        int n = readInt();
        int k = readInt();
        final int sz = n + m + 2;
        final List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < sz; ++i) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < k; ++i) {
            int a = readInt();
            int b = readInt();
            g.get(a).add(new Edge(b + m, 1));
        }
        for (int i = 1; i <= m; ++i) {
            g.get(0).add(new Edge(i, 1));    // add fake source
        }
        for (int i = m + 1; i <= m + n; ++i) {
            g.get(i).add(new Edge(n + m + 1, 1));      // add fake sink
        }
        final int[] previous = new int[sz];
        final int[] cap = new int[sz];
        final boolean[] used = new boolean[sz];

        class FlowUtils {
            void bfs(int start) {
                Arrays.fill(previous, -1);
                Arrays.fill(used, false);
                Arrays.fill(cap, 0);
                used[start] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(start);
                while (!q.isEmpty()) {
                    int from = q.poll();
                    for (int i = 0; i < g.get(from).size(); ++i) {
                        int to = g.get(from).get(i).to;
                        if (!used[to]) {
                            q.add(to);
                            previous[to] = from;
                            cap[to] = g.get(from).get(i).capacity;
                            used[to] = true;
                        }
                    }
                }
            }

            int maxFlow(int from, int to) {
                int ans = 0;
                while (true) {
                    bfs(from);
                    if (previous[to] == -1) {
                        return ans;
                    }
                    // reconstruct the path
                    // find min capacity edge
                    int t = to;
                    int min = Integer.MAX_VALUE;
                    List<Integer> path = new ArrayList<>();
                    while (previous[t] != -1) {
                        min = Math.min(min, cap[to]);
                        path.add(t);
                        t = previous[t];
                    }
                    path.add(t);
                    for (int i = 0; i < path.size() - 1; ++i) {
                        g.get(path.get(i)).add(new Edge(path.get(i + 1), min));
                        int s = path.get(i + 1);
                        int f = path.get(i);
                        // find edge from s to f and reduce cap on min value, or remove it, if cap == min
                        for (int j = 0; j < g.get(s).size(); ++j) {
                            if (g.get(s).get(j).to == f) {
                                int capacity = g.get(s).get(j).capacity;
                                if (capacity == min) {
                                    g.get(s).remove(j);
                                } else {
                                    g.get(s).remove(j);
                                    g.get(s).add(new Edge(f, capacity - min));
                                }
                                break;
                            }
                        }
                    }
                    ans += min;
                }
            }
        }

        FlowUtils flow = new FlowUtils();
        writer.println(n + m - flow.maxFlow(0, n + m + 1));
        writer.flush();
        writer.close();
    }

    private class Edge {

        int to;
        int capacity;

        private Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (capacity != edge.capacity) return false;
            if (to != edge.to) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = to;
            result = 31 * result + capacity;
            return result;
        }
    }
}
