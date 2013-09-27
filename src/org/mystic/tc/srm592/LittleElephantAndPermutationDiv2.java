package org.mystic.tc.srm592;

public class LittleElephantAndPermutationDiv2 {

    int[] p;

    public long getNumber(int N, int K) {
        int[] num = new int[N];
        for (int i = 0; i < N; ++i) {
            num[i] = N - i;
        }
        long res = 0;
        int[] sample = new int[N];
        p = new int[N];
        for (int i = 0; i < N; ++i) {
            p[i] = i + 1;
            sample[i] = i + 1;
        }
        for (int i = 0; i < fact(N); ++i) {
            int t = calcF(sample, p);
            if (t >= K)
                ++res;

            next_permutation();
        }
        return res * fact(N);
    }

    private int calcF(int[] sample, int[] a) {
        int ans = 0;
        int n = sample.length;
        for (int i = 0; i < n; ++i) {
            ans += Math.max(sample[i], a[i]);
        }
        return ans;
    }

    private boolean next_permutation() {
        for (int a = p.length - 2; a >= 0; --a)
            if (p[a] < p[a + 1])
                for (int b = p.length - 1; ; --b)
                    if (p[b] > p[a]) {
                        int t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for (++a, b = p.length - 1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
        return false;
    }

    private long fact(int n) {
        long ans = 1;
        for (int i = 2; i <= n; ++i) {
            ans *= i;
        }
        return ans;
    }

}