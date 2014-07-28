package org.mystic.codeforces.abbyy.cup30.finals;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
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
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        int n = nextInt();
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(n);
        int res = 0;
        if (n == 0) {
            out.println(0);
            return;
        }
        boolean isZero = true;
        while (isZero) {
            ++res;
            HashSet<Integer> newSet = new HashSet<Integer>();
            for (Integer x : set) {
                String s = Integer.toString(x);
                HashSet<Character> diff = new HashSet<Character>();
                for (int j = 0; j < s.length(); ++j) {
                    char c = s.charAt(j);
                    if (!diff.contains(c) && c != '0') {
                        diff.add(c);
                    }
                }
                int max = -1;
                for (Character c : diff) {
                    int z = c - '0';
                    if (x - z == 0) {
                        out.println(res);
                        isZero = false;
                        break;
                    } else {
                        max = Math.max(max, z);
                    }
                }
                newSet.add(x - max);
            }
            set = newSet;
        }

    }

    public int radius(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        int[][] g = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i].charAt(j) == '#') {
                    g[i][j] = 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n + 1; ++i) {
            for (int j = 0; j < m + 1; ++j) {
                // try to draw circle from (i,j);
                int maxR = Math.min(n - i - 1, Math.min(m - j - 1, Math.min(i, j)));
                for (int k = 1; k <= maxR; ++k) {
                    boolean isOk = true;
                    for (int a1 = 0; a1 < n; ++a1) {
                        for (int b1 = 0; b1 < m; ++b1) {
                            if (g[a1][b1] == 1) {
                                int x1 = a1;
                                int x2 = a1 + 1;
                                int y1 = b1;
                                int y2 = b1 + 1;
                                double x = Math.sqrt(k * k - x1 * x1);
                                if (Math.abs(x - x1) < 1e-8 || Math.abs(x - x2) < 1e-8) {

                                } else if (x >= x1 && x <= x2) {
                                    isOk = false;
                                }
                                x = Math.sqrt(k * k - x2 * x2);
                                if (Math.abs(x - x1) < 1e-8 || Math.abs(x - x2) < 1e-8) {

                                } else if (x >= x1 && x <= x2) {
                                    isOk = false;
                                }
                                double y = Math.sqrt(k * k - y1 * y1);
                                if (Math.abs(y - y1) < 1e-8 || Math.abs(y - y2) < 1e-8) {

                                } else if (y >= y1 && y <= y2) {
                                    isOk = false;
                                }
                                y = Math.sqrt(k * k - y2 * y2);
                                if (Math.abs(y - y1) < 1e-8 || Math.abs(y - y2) < 1e-8) {

                                } else if (y >= y1 && y <= y2) {
                                    isOk = false;
                                }
                            }
                        }
                    }
                    if (isOk) {
                        ans = Math.max(k, ans);
                    }
                }
            }
        }
        return ans;
    }


}