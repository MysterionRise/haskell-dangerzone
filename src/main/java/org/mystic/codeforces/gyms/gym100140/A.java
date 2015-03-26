package org.mystic.codeforces.gyms.gym100140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class A {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public A() throws IOException {

        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        new A().solve();
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
        final int[][] cap = new int[n][n];
        for (int i = 0; i < k; ++i) {
            int a = readInt() - 1;
            int b = readInt() - 1;
            int c = readInt();
            cap[a][b] = c;
            cap[b][a] = c;
        }
        class PushFlowUtils {


            public int maxFlow(int from, int to) {
                int[] height = new int[n];
                height[from] = n - 1;
                int[] maxh = new int[n];
                int[][] f = new int[n][n];
                int[] e = new int[n];

                for (int i = 0; i < n; ++i) {
                    f[from][i] = cap[from][i];
                    f[i][from] = -f[from][i];
                    e[i] = cap[from][i];
                }

                for (int sz = 0; ; ) {
                    if (sz == 0) {
                        for (int i = 0; i < n; ++i)
                            if (i != from && i != to && e[i] > 0) {
                                if (sz != 0 && height[i] > height[maxh[0]])
                                    sz = 0;
                                maxh[sz++] = i;
                            }
                    }
                    if (sz == 0)
                        break;
                    while (sz != 0) {
                        int i = maxh[sz - 1];
                        boolean pushed = false;
                        for (int j = 0; j < n && e[i] != 0; ++j) {
                            if (height[i] == height[j] + 1 && cap[i][j] - f[i][j] > 0) {
                                int df = Math.min(cap[i][j] - f[i][j], e[i]);
                                f[i][j] += df;
                                f[j][i] -= df;
                                e[i] -= df;
                                e[j] += df;
                                if (e[i] == 0)
                                    --sz;
                                pushed = true;
                            }
                        }
                        if (!pushed) {
                            height[i] = Integer.MAX_VALUE;
                            for (int j = 0; j < n; ++j)
                                if (height[i] > height[j] + 1 && cap[i][j] - f[i][j] > 0)
                                    height[i] = height[j] + 1;
                            if (height[i] > height[maxh[0]]) {
                                sz = 0;
                                break;
                            }
                        }
                    }
                }

                int flow = 0;
                for (int i = 0; i < n; i++)
                    flow += f[from][i];

                return flow;
            }
        }

        PushFlowUtils f = new PushFlowUtils();
        writer.println(f.maxFlow(0, n - 1));
        writer.flush();
    }
}
