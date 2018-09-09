package org.mystic.tc.srm590;

import java.util.ArrayList;

public class FoxAndChess {

    private int[] getPos(String s) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '.') {
                a.add(i);
            }
        }
        int[] ans = new int[a.size()];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = a.get(i);
        }
        return ans;
    }

    public String ableToMove(String begin, String target) {
        char[] s = begin.replace(".", "").toCharArray();
        char[] f = target.replace(".", "").toCharArray();
        int[] pos1 = getPos(begin);
        int[] pos2 = getPos(target);
        int n = f.length;
        int m = s.length;
        if (n != m) {
            return "Impossible";
        }
        for (int i = 0; i < n; ++i) {
            if (s[i] != f[i]) {
                return "Impossible";
            }
            if (begin.charAt(pos1[i]) == 'R' && pos2[i] < pos1[i]) {
                return "Impossible";
            }
            if (begin.charAt(pos1[i]) == 'L' && pos2[i] > pos1[i]) {
                return "Impossible";
            }
        }
        return "Possible";
    }
}
