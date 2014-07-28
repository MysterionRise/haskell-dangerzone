package org.mystic.tc.srm593;

public class RaiseThisBarn {

    public int calc(String str) {
        int ans = 0;
        char[] c = str.toCharArray();
        int n = c.length;
        for (int i = 1; i <= n - 1; ++i) {
            int c1 = 0;
            for (int j = 0; j < i; ++j) {
                if (c[j] == 'c') {
                    ++c1;
                }
            }
            int c2 = 0;
            for (int j = i; j < n; ++j) {
                if (c[j] == 'c') {
                    ++c2;
                }
            }
            if (c1 == c2) {
                ans++;
            }
        }
        return ans;
    }
}
