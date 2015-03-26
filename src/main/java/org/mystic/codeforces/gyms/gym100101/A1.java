package org.mystic.codeforces.gyms.gym100101;

import java.io.*;
import java.util.StringTokenizer;

public class A1 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A1()).start();
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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("sum0.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("sum0.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        int n = nextInt();
        int x = nextInt();
        int y = nextInt();
        int a0 = nextInt();
        int m = nextInt();
        int z = nextInt();
        int t = nextInt();
        int b0 = nextInt();
        int two16 = (int) Math.pow(2, 16);
        int two30 = (int) Math.pow(2, 30);
        long[] prefixSum = new long[n + 1];
        long sum = a0;
        prefixSum[0] = 0;
        for (int i = 1; i < n; ++i) {
            int ai = ((x * a0) % two16 + y) % two16;
            prefixSum[i] = sum;
            sum += ai;
            a0 = ai;
        }
        prefixSum[n] = sum;
        sum = 0;
        int c0 = b0 % n;
        int bi1 = b0;
        for (int i = 1; i < 2 * m; ++i) {
            int zz = ((z * bi1) % two30 + t) % two30;
            if (zz < 0) {
                zz += two30;
            }
            int b1 = zz % n;
            if (b1 < 0) {
                b1 += n;
            }
            int c1 = b1;
            bi1 = zz;
            if (i % 2 == 1) {
                sum += (prefixSum[Math.max(c0, c1) + 1] - prefixSum[Math.min(c0, c1)]);
            } else {
                c0 = c1;
            }
        }
        out.println(sum);
    }

}
