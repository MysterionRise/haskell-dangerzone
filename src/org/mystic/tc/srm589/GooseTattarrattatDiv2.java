package org.mystic.tc.srm589;

public class GooseTattarrattatDiv2 {
    public int getmin(String S) {
        char[] arr = S.toCharArray();
        int[] m = new int[26];
        for (int i = 0; i < arr.length; ++i) {
            m[arr[i] - 'a']++;
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
