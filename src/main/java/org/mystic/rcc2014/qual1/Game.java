package org.mystic.rcc2014.qual1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Game implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new Game()).start();
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
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
        int T = nextInt();
        for (int i = 1; i <= T; ++i) {
            int n = nextInt();
            int[][] mat = new int[n + 2][n + 2];
            for (int j = 0; j <= n + 1; ++j) {
                Arrays.fill(mat[j], -1);
            }
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= n; ++k) {
                    mat[j][k] = nextInt();
                }
            }
            boolean isOk = false;
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= n; ++k) {
                    if (mat[j][k] == 0) {
                        isOk = true;
                    }
                }
            }
            int[] dx = new int[]{1, 0, -1, 0};
            int[] dy = new int[]{0, 1, 0, -1};
            for (int j = 1; j <= n; ++j) {
                for (int k = 1; k <= n; ++k) {
                    for (int l = 0; l < 4; ++l) {
                        if (mat[j][k] == mat[j + dx[l]][k + dy[l]]) {
                            isOk = true;
                        }
                    }
                    if (mat[j][k] == 0) {
                        isOk = true;
                    }
                }
            }
            if (isOk) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
}
