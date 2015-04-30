package org.mystic.codeforces.cf101_200.cf197div2;

import java.io.*;
import java.util.StringTokenizer;

public class D implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new D()).start();
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
        int m = nextInt();
        int z = (int) Math.pow(2, n);
        int[] a = new int[z];
        for (int i = 0; i < z; ++i) {
            a[i] = nextInt();
        }
        int t = (int) Math.pow(2, n + 1) - 1;
        int[] tree = new int[t + 1];
        for (int i = t, j = z - 1; i > t - z; --i, --j) {
            tree[i] = a[j];
        }
        int layer = 1;
        int temp = n - 1;
        for (int i = t - z; i >= 1; --i) {
            tree[i] = operation(tree[2 * i], tree[2 * i + 1], layer);
            if (i == Math.pow(2, temp)) {
                layer++;
                temp--;
            }
        }
        for (int i = 0; i < m; ++i) {
            int p = nextInt();
            int b = nextInt();
            int pos = ((int) (Math.pow(2, n) + p - 1));
            tree[pos] = b;
            layer = 1;
            while (pos != 1) {
                if (pos % 2 == 0) {
                    tree[pos / 2] = operation(tree[pos], tree[pos + 1], layer);
                } else {
                    tree[pos / 2] = operation(tree[pos - 1], tree[pos], layer);
                }
                pos /= 2;
                ++layer;
            }
            out.println(tree[1]);
        }
    }

    private int operation(int x, int y, int layer) {
        if (layer % 2 == 1) {
            return x | y;
        } else {
            return x ^ y;
        }
    }
}
