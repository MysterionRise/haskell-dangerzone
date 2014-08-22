package org.mystic.tc.srm630;


import java.util.*;

public class Egalitarianism3Easy {

    public int maxCities(int n, int[] a, int[] b, int[] len) {
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], Integer.MAX_VALUE);
            g[i][i] = 0;
        }
        for (int i = 0; i < n - 1; ++i) {
            g[a[i] - 1][b[i] - 1] = len[i];
            g[b[i] - 1][a[i] - 1] = len[i];
        }
        for (int k = 0; k < n; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (i != j && g[i][k] < Integer.MAX_VALUE && g[k][j] < Integer.MAX_VALUE)
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }

        int ans = 1;
        for (int i = 0; i < n; ++i) {
            Set<Integer> s = new HashSet<>();
            for (int j = i + 1; j < n; ++j) {
                s.add(g[i][j]);
            }
            final Iterator<Integer> it = s.iterator();
            while (it.hasNext()) {
                int v = it.next();
                List<Integer> pool = new ArrayList<>();
                pool.add(i);
                for (int j = i + 1; j < n; ++j) {
                    if (g[i][j] == v) {
                        pool.add(j);
                    }
                }
                int l = pool.size();
                Set<Integer> d = new HashSet<>();
                for (int j = 0; j < l; ++j) {
                    for (int k = 0; k < l; ++k) {
                        if (k != j) {
                            d.add(g[pool.get(k)][pool.get(j)]);
                        }
                    }
                }
                if (d.size() == 1) {
                    ans = Math.max(ans, pool.size());
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Egalitarianism3Easy e = new Egalitarianism3Easy();
        int p0;
        int[] p1;
        int[] p2;
        int[] p3;
        p0 = 6;
        p1 = new int[]{1, 2, 3, 2, 3};
        p2 = new int[]{2, 3, 4, 5, 6};
        p3 = new int[]{2, 1, 3, 2, 3};
        System.out.println(e.maxCities(p0, p1, p2, p3));
    }
}
