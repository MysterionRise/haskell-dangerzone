package org.mystic.tc.srm602;

public class TypoCoderDiv2 {
    public int count(int[] rating) {
        int ans = 0;
        int rat = 500;
        for (int aRating : rating) {
            if (aRating >= 1200 && rat < 1200) {
                ++ans;
            } else if (rat >= 1200 && aRating < 1200) {
                ++ans;
            }
            rat = aRating;
        }
        return ans;
    }


}
