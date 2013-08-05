package org.mystic.codeforces;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * @author kperikov
 */
public class B1_331 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new B1_331()).start();
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
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        int q = nextInt();
        for (int qq = 0; qq < q; ++qq) {
            int t = nextInt();
            int a = nextInt();
            int b = nextInt();
            if (t == 1) {
                int[] dp = new int[n];
                for (int i = 0; i < n; ++i) {
                    if (arr[i] >= a && arr[i] <= b && dp[i] == 0) {
                        dp[i] = arr[i];
                        int temp = arr[i];
                        for (int j = i + 1; j < n; ++j) {
                            if (arr[j] == temp + 1) {
                                dp[j] = arr[i];
                                ++temp;
                            }
                        }
                    }
                }
                HashSet<Integer> s = new HashSet<Integer>();
                for (int i = 0; i < n; ++i) {
                    if (!s.contains(dp[i]) && dp[i] != 0) {
                        s.add(dp[i]);
                    }
                }
                out.println(s.size());

            } else {
                int temp = arr[a - 1];
                arr[a - 1] = arr[b - 1];
                arr[b - 1] = temp;
            }
        }

    }

}