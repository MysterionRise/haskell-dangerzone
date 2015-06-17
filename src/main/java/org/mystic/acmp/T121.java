package org.mystic.acmp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class T121 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T121()).start();
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
//            br = new BufferedReader(new FileReader("input.txt"));
//            out = new PrintWriter(new FileWriter("output.txt"));
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int arr[] = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        Arrays.sort(arr);
        final int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = arr[1] - arr[0];
        if (n > 2) {
            dp[2] = arr[2] - arr[0];
        }
        for (int i = 3; i < n; ++i) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + arr[i] - arr[i - 1];
        }
        out.println(dp[n - 1]);

    }
}
