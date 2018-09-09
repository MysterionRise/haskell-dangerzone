package org.mystic.tc.srm589;

public class GooseTattarrattatDiv2 {
    public int getmin(String S) {
        char[] arr = S.toCharArray();
        int[] m = new int[26];
        for (char anArr : arr) {
            m[anArr - 'a']++;
        }
        int max = -1;
        int pos = -1;
        for (int i = 0; i < 26; ++i) {
            if (m[i] > max) {
                max = m[i];
                pos = i;
            }
        }
        return S.length() - max;
    }
}
