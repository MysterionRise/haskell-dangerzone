package org.mystic.tc.srm588;

/**
 * @author kperikov
 */
public class KeyDungeonDiv2 {
    public int countDoors(int[] doorR, int[] doorG, int[] keys) {
        int n = doorR.length;
        int w = keys[2];
        int r = keys[0];
        int g = keys[1];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (doorR[i] <= r) {
                if (doorG[i] <= g) {
                    ans++;
                } else if (doorG[i] <= g + w) {
                    ans++;
                }
            } else if (doorR[i] <= r + w) {
                int left = (w - (doorR[i] - r));
                if (doorG[i] <= g) {
                    ans++;
                } else if (doorG[i] <= g + left) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
