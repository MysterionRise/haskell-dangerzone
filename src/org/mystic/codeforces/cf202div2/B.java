package org.mystic.codeforces.cf202div2;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class B implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;
    ArrayList<Integer> ans = new ArrayList<>();

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
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        int v = nextInt();
        int[] num = new int[10];
        for (int i = 1; i < 10; ++i) {
            num[i] = nextInt();
        }
        int[][] dp = new int[10][v + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= v; ++i) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < 10; ++i) {
            for (int j = 0; j <= v; ++j) {
                if (j < num[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - num[i]] + i);
                }
            }
        }
        findAns(dp, 9, v, num);
        if (ans.size() == 0) {
            out.println(-1);
            return;
        }
        Collections.sort(ans);
        for (int i = ans.size() - 1; i >= 0; --i) {
            for (int j = 0; j < dp[ans.get(i)][v] / ans.get(i); ++j) {
                out.print(ans.get(i));
            }
        }
    }

    private void findAns(int[][] A, int k, int s, int[] w) {
        if (A[k][s] == 0) {
            return;
        }
        if (A[k - 1][s] == A[k][s]) {
            findAns(A, k - 1, s, w);
        } else {
            findAns(A, k - 1, s - w[k] * (A[k][s] / k), w);
            ans.add(k);
        }
    }
}
