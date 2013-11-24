package org.mystic.codeforces.cf214div2;

import java.io.*;
import java.util.StringTokenizer;

//@todo failed
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
        final int n = nextInt();
        final int k = nextInt();
        final int[] a = new int[n];
        final int[] b = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        for (int i = 0; i < n; ++i) {
            b[i] = nextInt();
        }
        class Utils {
            int brute(int[] a, int[] b, int s, int f) {
                int z = f - s;
                int max = Integer.MIN_VALUE;
                double pow = Math.pow(2, z);
                for (int i = 0; i <= pow; ++i) {
                    char[] chars = Integer.toBinaryString(i).toCharArray();
                    int top = 0;
                    int btm = 0;
                    for (int j = 0; j < chars.length; ++j) {
                        if (chars[j] == '1') {
                            top += a[s + j];
                            btm += b[s + j];
                        }
                    }
                    if (btm != 0 && top / btm == k) {
                        max = Math.max(max, top);
                    }
                }
                if (max == Integer.MIN_VALUE) {
                    return -1;
                }
                return max;
            }
        }

        Utils u = new Utils();
        int ans = 0;
        final int dividers = 8;
        if (n < dividers) {
            ans = u.brute(a, b, 0, n - 1);
        } else {
            int x = n / dividers;
            for (int i = 0; i < dividers; ++i) {
                int tmp;
                if (x * i + x < n) {
                    tmp = u.brute(a, b, x * i, x * i + x);
                } else {
                    tmp = u.brute(a, b, x * i, n - 1);
                }
                if (tmp != -1) {
                    ans += tmp;
                }
            }
        }
        if (ans == 0) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }
}
