package org.mystic.tc.srm602;

import java.util.Arrays;

//TODO not working
public class PilingRectsDiv2 {

    public static void main(String[] args) {
        PilingRectsDiv2 w = new PilingRectsDiv2();
        System.out.println(w.getmax(new int[]{1,2,3,1}, new int[]{3,2,4,4}, 3));
    }

    public int getmax(int[] X, int[] Y, int limit) {
        final int n = X.length;
        Rect[] rects = new Rect[n];
        for (int i = 0; i < n; ++i) {
            rects[i] = new Rect(X[i], Y[i]);
        }
        final boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    if (includes(rects[i], rects[j])) {
                        g[i][j] = true;
                    }
                }
            }
        }

        final boolean[] used = new boolean[n];
        final int[] path = new int[n];
        final int[] length = new int[n];
        class DFSUtils {

            public void dfs(int v) {
                for (int i = 0; i < n; ++i) {
                    if (g[v][i]) {
                        dfs(i);
                        if (length[i] < length[v] + 1) {
                            path[i] = v;
                            length[i] = length[v] + 1;
                        }
                    }
                }
            }


        }
        int ans = -1;
        DFSUtils dfsUtils = new DFSUtils();
        for (int i = 0; i < n; ++i) {
            Arrays.fill(used, false);
            Arrays.fill(length, 0);
            length[i] = 0;
            path[i] = -1;
            dfsUtils.dfs(i);
            int max = -1;
            int ind = 0;
            for (int j = 0; j < n; ++j) {
                if (max < length[j] + 1) {
                    max = length[j] + 1;
                    ind = j;
                }
            }
            if (ans < max) {
                while (path[ind] != -1) {
                    ind = path[ind];
                }
                if (rects[ind].getSq() >= limit) {
                    ans = max;
                }
            }
        }


        return ans;
    }

    boolean includes(Rect r1, Rect r2) {
        if ((r1.x <= r2.x && r1.y <= r2.y) || (r1.y <= r2.x && r1.x <= r2.y)) {
            return true;
        }
        return false;
    }

    class Rect {
        int x;
        int y;

        Rect(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getSq() {
            return x * y;
        }
    }
}
