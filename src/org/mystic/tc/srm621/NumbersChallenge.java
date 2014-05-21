package org.mystic.tc.srm621;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class NumbersChallenge {

    public int MinNumber(int[] S) {
        int n = S.length;
        Arrays.sort(S);
        Set<Integer> s = new HashSet<>();
        for (int i = 1; i <= (1 << n); ++i) {
            int sum = 0;
            int mask;
            for (int j = 0; j < n; ++j) {
                mask = (1 << j);
                if ((mask & i) != 0) {
                    sum += S[j];
                }
            }
            s.add(sum);
        }
        for (int i = 1; i <= n * 100_000; ++i) {
            if (!s.contains(i)) {
                return i;
            }
        }
        return n * 100_000;
    }

    public static void main(String[] args) {
        NumbersChallenge n = new NumbersChallenge();
        System.out.println(n.MinNumber(new int[]{5, 1, 2}));
    }

}