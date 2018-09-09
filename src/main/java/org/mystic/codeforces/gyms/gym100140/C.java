package org.mystic.codeforces.gyms.gym100140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class C {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public C() throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new C().solve();
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
        final int n = readInt();
        int k = readInt();
        final List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < k; ++i) {
            int a = readInt() - 1;
            int b = readInt() - 1;
            g.get(a).add(new Edge(b, 1));
            g.get(b).add(new Edge(a, 1));
        }
        final int[] previous = new int[n];
        final boolean[] used = new boolean[n];
        final int[] capacity = new int[n];
        class FlowUtils {
            void bfs(int start) {
                Arrays.fill(previous, -1);
                Arrays.fill(used, false);
                Arrays.fill(capacity, 0);
                Queue<Integer> q = new LinkedList<>();
                used[start] = true;
                previous[start] = -1;
                q.add(start);
                while (!q.isEmpty()) {
                    int from = q.poll();
                    for (int i = 0; i < g.get(from).size(); ++i) {
                        int to = g.get(from).get(i).to;
                        if (!used[to]) {
                            q.add(to);
                            previous[to] = from;
                            capacity[to] = g.get(from).get(i).capacity;
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

                    int t = to;
                    int min = Integer.MAX_VALUE;
                    ArrayList<Integer> path = new ArrayList<>();
                    while (previous[t] != -1) {
                        path.add(t);
                        min = Math.min(min, capacity[t]);
                        t = previous[t];
                    }
                    path.add(t);
                    boolean findEdge = false;
                    for (int i = 0; i < path.size() - 1 && !findEdge; ++i) {
                        int finish = path.get(i);
                        int start = path.get(i + 1);
                        for (int j = 0; j < g.get(start).size() && !findEdge; ++j) {
                            if (g.get(start).get(j).to == finish) {
                                int cap = g.get(start).get(j).capacity;
                                if (cap == min) {
                                    g.get(start).remove(j);
                                } else {
                                    g.get(start).remove(j);
                                    g.get(start).add(new Edge(finish, cap - min));
                                }
                                boolean find = false;
                                for (int k = 0; k < g.get(t).size(); ++k) {
                                    if (g.get(finish).get(k).to == start) {
                                        g.get(finish).remove(k);
                                        g.get(finish).add(new Edge(start, cap + min));
                                        find = true;
                                        break;
                                    }
                                }
                                if (!find) {
                                    g.get(finish).add(new Edge(start, min));
                                }
                                findEdge = true;
                                break;
                            }
                        }
                    }
                    ans += min;
                }


            }
        }

        FlowUtils f = new FlowUtils();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                 ans = Math.max(ans, f.maxFlow(i, j));
            }
        }
        writer.println(ans);
        writer.flush();
    }

    class Edge {
        int to;
        int capacity;

        Edge(int to, int capacity) {
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
