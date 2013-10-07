package org.mystic.opencup.round1;

import java.io.*;
import java.util.Arrays;

public class H {

    int[] tt = null;
    int tx = 0;
    private BufferedReader reader;
    private PrintWriter writer;

    public H() throws IOException {

        reader = new BufferedReader(new FileReader("reachability.in"));
        writer = new PrintWriter(new FileWriter("reachability.out"));
    }

    public static void main(String[] args) throws IOException {
        new H().solve();
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

    long foo(int[][] g, int A, int B) {
        long mod = ((1L << 32) - 1);
        long ret = 0;
        long a = 1;
        int n = g.length;
        for (int i = 0; i < n; i++) {
            long b = 1;
            for (int j = 0; j < n; j++) {
                ret += a * b * (g[i][j] == 0 ? 0 : 1);
                ret &= mod;
                b *= B;
                b &= mod;
            }
            a *= A;
            a &= mod;
        }
        return ret;
    }

    int dfs(int[][] g, int u, int[] l, int ls, boolean[] used) {
        if (used[u]) {
            return ls;
        }
        used[u] = true;
        l[ls++] = u;
        for (int v = 0; v < g.length; v++) {
            if (g[u][v] == 1) {
                ls = dfs(g, v, l, ls, used);
            }
        }
        return ls;
    }

    private void solve() throws IOException {
        int n = readInt();
        int[][] g = new int[n][n];
        int[][] r = new int[n][n];
        int q = readInt();
        int A = readInt();
        int B = readInt();
        int[] l1 = new int[500];
        boolean[] used = new boolean[500];
        int[] l2 = new int[500];
        for (int k = 0; k < q; k++) {
            String[] s = reader.readLine().split(" ");
            int v = Integer.parseInt(s[2]) - 1;
            int[] nums = new int[s.length - 4];
            for (int i = 4; i < s.length; i++) {
                nums[i - 4] = Integer.parseInt(s[i]) - 1;
            }
            if ("+".equals(s[0])) {
                if ("o".equals(s[1])) {
                    int ls1 = 0, ls2 = 0;
                    Arrays.fill(used, false);
                    for (int i = 0; i < nums.length; i++) {
                        ls1 = dfs(g, nums[i], l1, ls1, used);
                    }
                    Arrays.fill(used, false);
                    ls2 = dfs(r, v, l2, ls2, used);
                    for (int i = 0; i < ls2; i++) {
                        int u = l2[i];
                        for (int j = 0; j < ls1; j++) {
                            int uu = l1[j];
                            g[u][uu]++;
                            r[uu][u]++;
                        }
                    }
                    writer.println(foo(g, A, B));
                } else {
                    int ls1 = 0, ls2 = 0;
                    Arrays.fill(used, false);
                    for (int i = 0; i < nums.length; i++) {
                        ls2 = dfs(r, nums[i], l2, ls2, used);
                    }
                    Arrays.fill(used, false);
                    ls1 = dfs(g, v, l1, ls1, used);
                    for (int i = 0; i < ls2; i++) {
                        int u = l2[i];
                        for (int j = 0; j < ls1; j++) {
                            int uu = l1[j];
                            g[u][uu]++;
                            r[uu][u]++;
                        }
                    }
                    writer.println(foo(g, A, B));
                }

            } else {
                if ("o".equals(s[1])) {
                    int ls1 = 0, ls2 = 0;
                    Arrays.fill(used, false);
                    for (int i = 0; i < nums.length; i++) {
                        ls1 = dfs(g, nums[i], l1, ls1, used);
                    }
                    Arrays.fill(used, false);
                    ls2 = dfs(r, v, l2, ls2, used);
                    for (int i = 0; i < ls2; i++) {
                        int u = l2[i];
                        for (int j = 0; j < ls1; j++) {
                            int uu = l1[j];
                            g[u][uu]--;
                            r[uu][u]--;
                        }
                    }
                    writer.println(foo(g, A, B));
                } else {
                    int ls1 = 0, ls2 = 0;
                    Arrays.fill(used, false);
                    for (int i = 0; i < nums.length; i++) {
                        ls2 = dfs(r, nums[i], l2, ls2, used);
                    }
                    Arrays.fill(used, false);
                    ls1 = dfs(g, v, l1, ls1, used);
                    for (int i = 0; i < ls2; i++) {
                        int u = l2[i];
                        for (int j = 0; j < ls1; j++) {
                            int uu = l1[j];
                            g[u][uu]--;
                            r[uu][u]--;
                        }
                    }
                    writer.println(foo(g, A, B));
                }

            }
        }
        writer.flush();
    }
}
