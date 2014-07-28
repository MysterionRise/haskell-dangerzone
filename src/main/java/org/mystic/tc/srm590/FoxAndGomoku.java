package org.mystic.tc.srm590;

public class FoxAndGomoku {
    public String win(String[] board) {
        int n = board.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = 0;
                for (int k = 0; k < 5; ++k) {
                    if (i + k < n && board[i + k].charAt(j) == 'o') {
                        ++cnt;
                    } else {
                        cnt = 0;
                    }
                }
                if (cnt == 5) {
                    return "found";
                }
                cnt = 0;
                for (int k = 0; k < 5; ++k) {
                    if (j + k < n && board[i].charAt(j + k) == 'o') {
                        ++cnt;
                    } else {
                        cnt = 0;
                    }
                }
                if (cnt == 5) {
                    return "found";
                }
                cnt = 0;
                for (int k = 0; k < 5; ++k) {
                    if (i + k < n && j + k < n && board[i + k].charAt(j + k) == 'o') {
                        ++cnt;
                    } else {
                        cnt = 0;
                    }
                }
                if (cnt == 5) {
                    return "found";
                }
                cnt = 0;
                for (int k = 0; k < 5; ++k) {
                    if (i - k >= 0 && i - k < n && j + k < n && board[i - k].charAt(j + k) == 'o') {
                        ++cnt;
                    } else {
                        cnt = 0;
                    }
                }
                if (cnt == 5) {
                    return "found";
                }
            }
        }
        return "not found";
    }
}