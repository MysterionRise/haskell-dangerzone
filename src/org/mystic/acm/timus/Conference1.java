package org.mystic.acm.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Conference1 {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public Conference1() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new Conference1().solve();
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
        final int[][] c = new int[sz][sz];
        final int[][] f = new int[sz][sz];
        final int[] ptr = new int[sz];
        final int[] q = new int[sz];
        final int[] dist = new int[sz];
        class DinicUtils {
            public int maxFlow(int s, int t) {
                int flow = 0;
                while (true) {
                    // if dont have path between s and t -> end of algorithm, let's return answer
                    if (!bfs(s, t)) {
                        break;
                    }
                    Arrays.fill(ptr, 0);
                    int pushed = dfs(s, t, Integer.MAX_VALUE);
                    while (pushed != 0) {
                        flow += pushed;
                        pushed = dfs(s, t, Integer.MAX_VALUE);
                    }
                }
                return flow;
            }

            public boolean bfs(int s, int t) {
                int qh = 0, qt = 0;
                q[qt++] = s;
                Arrays.fill(dist, -1);
                dist[s] = 0;
                while (qh < qt) {
                    int v = q[qh++];
                    for (int to = 0; to < sz; ++to)
                        if (dist[to] == -1 && f[v][to] < c[v][to]) {
                            q[qt++] = to;
                            dist[to] = dist[v] + 1;
                        }
                }
                return dist[t] != -1;
            }

            public int dfs(int v, int t, int flow) {
                if (flow == 0) return 0;
                if (v == t) return flow;
                for (int to = ptr[v]; to < sz; ++to) {
                    if (dist[to] != dist[v] + 1) continue;
                    int pushed = dfs(to, t, Math.min(flow, c[v][to] - f[v][to]));
                    if (pushed != 0) {
                        f[v][to] += pushed;
                        f[to][v] -= pushed;
                        return pushed;
                    }
                }
                return 0;
            }
        }

        for (int i = 0; i < k; ++i) {
            int a = readInt();
            int b = readInt();
            c[a][b + m] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            c[0][i] = 1;        // add fake source
        }
        for (int i = m + 1; i <= m + n; ++i) {
            c[i][n + m + 1] = 1; // add fake sink
        }
        DinicUtils d = new DinicUtils();
        writer.println(n + m - d.maxFlow(0, n + m + 1));
        writer.flush();
    }

}
