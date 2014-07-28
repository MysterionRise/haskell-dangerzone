package org.mystic.tc.srm529;

public class PairingPawns {

    public int savedPawnCount(int[] start) {
        int n = start.length;
        for (int i = n - 1; i > 0; --i) {
            start[i - 1] += start[i] / 2;
        }
        return start[0];
    }
}
