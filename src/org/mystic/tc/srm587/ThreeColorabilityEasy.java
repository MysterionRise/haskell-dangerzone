package org.mystic.tc.srm587;

/**
 * @author kperikov
 */
public class ThreeColorabilityEasy {

    public String isColorable(String[] cells) {
        int n = cells.length;
        int m = cells[0].length();
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < m - 1; ++j) {
                int cntN = 0;
                if (cells[i].charAt(j) == 'N') {
                    cntN++;
                }
                if (cells[i].charAt(j + 1) == 'N') {
                    cntN++;
                }
                if (cells[i + 1].charAt(j) == 'N') {
                    cntN++;
                }
                if (cells[i + 1].charAt(j + 1) == 'N') {
                    cntN++;
                }
                if (cntN == 1 || cntN == 3) {
                    return "No";
                }
            }
        }
        return "Yes";
    }
}
