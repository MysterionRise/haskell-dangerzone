package org.mystic.tc.srm629;

public class RectangleCoveringEasy {
    public int solve(int holeH, int holeW, int boardH, int boardW) {
        int max = Math.max(holeH, holeW);
        int min = Math.min(holeH, holeW);
        int maxB = Math.max(boardH, boardW);
        int minB = Math.min(boardH, boardW);
        if (maxB > max && minB > min) {
            return 1;
        }
        if (maxB >= max && minB > min) {
            return 1;
        }
        if (maxB > max && minB >= min) {
            return 1;
        }
        return -1;
    }
}