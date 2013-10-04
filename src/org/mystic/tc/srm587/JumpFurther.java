package org.mystic.tc.srm587;

public class JumpFurther {

    public int furthest(int N, int badStep) {
        int ans = 0;
        int[] dontDo = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            if (ans + i != badStep) {
                ans += i;
            } else {
                for (int j = 1; j <= N; ++j) {
                    if (dontDo[j] == 0 && j < i) {
                        dontDo[j] = 1;
                        ans -= j;
                        ans += i;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
