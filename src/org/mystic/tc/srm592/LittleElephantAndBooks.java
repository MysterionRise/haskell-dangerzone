package org.mystic.tc.srm592;

import java.util.Arrays;

public class LittleElephantAndBooks {

    public int getNumber(int[] pages, int number) {
        Arrays.sort(pages);
        int ans = 0;
        for (int i = 0; i < number - 1; ++i) {
            ans += pages[i];
        }
        ans += pages[number];
        return ans;
    }
}