package org.mystic.codeforces.bayan2015;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public void solve() throws IOException {

        class SegmentTree {
            int N;
            int[] tree;
            int[] mul;

            void build(int[] a) {
                mul = new int[tree.length];
                for (int i = N; i < tree.length; ++i) {
                    tree[i] = a[i - N];
                    mul[i] = 0;
                }
                for (int i = N - 1; i >= 1; --i) {
                    tree[i] = gcd(tree[2 * i], tree[2 * i + 1]);
                    if (mul[2 * i] == mul[2 * i + 1] && mul[2 * i] == 0) {
                        mul[i] = 1;
                    } else if (mul[2 * i] == mul[2 * i + 1] && mul[2 * i] == 1) {
                        mul[i] = 4;
                    } else {
                        mul[i] = mul[2 * i] * mul[2 * i + 1];
                    }
                }
            }

            int treeGcd(int l, int r) {
                int gcd = 0;
                int left = l + N;
                int right = r + N;
                while (left < right) {
                    if (left % 2 == 1) {
                        gcd = gcd(gcd, tree[left]);
                        left++;
                    }
                    if (right % 2 != 1) {
                        gcd = gcd(gcd, tree[right]);
                        right--;
                    }
                    left /= 2;
                    right /= 2;
                }
                if (left == right) {
                    return gcd(gcd, tree[left]);
                }
                return gcd;
            }
        }
        final int n = nextInt();
        SegmentTree s = new SegmentTree();
        int size = 1;
        while (size < n) {
            size *= 2;
        }
        s.N = size;
        s.tree = new int[size * 2];
        int[] a = new int[s.N];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
        }
        s.build(a);
        final Map<Integer, Long> m = new HashMap<>();
        for (int i = s.tree.length - 1 - (s.N - n); i >= 1; --i) {
            final int key = s.tree[i];
            final Long z = m.get(key);
            int mul = s.mul[i];
            if (z == null) {
                m.put(key, 1L * mul);
            } else {
                m.put(key, z + (1L * mul));
            }
        }
        final int q = nextInt();
        for (int i = 0; i < q; ++i) {
            final int qi = nextInt();
            final Long x = m.get(qi);
            if (x == null) {
                out.println(0);
            } else {
                out.println(x);
            }
        }
    }
}
