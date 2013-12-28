package org.mystic.tc.srm602;

public class TypoCoderDiv2 {
    public int count(int[] rating) {
        int ans = 0;
        int rat = 500;
        for (int i = 0; i < rating.length; ++i) {
            if (rating[i] >= 1200 && rat < 1200) {
                ++ans;
            } else if (rat >= 1200 && rating[i] < 1200) {
                ++ans;
            }
            rat = rating[i];
        }
        return ans;
    }


}
