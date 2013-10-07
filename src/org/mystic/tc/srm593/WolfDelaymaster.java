package org.mystic.tc.srm593;

public class WolfDelaymaster {

    public static void main(String[] args) {
        WolfDelaymaster w = new WolfDelaymaster();
        System.out.println(w.check("wolfwwoollff"));
    }

    public String check(String str) {
        int n = str.length();
        char[] c = str.toCharArray();
        int currW = 0;
        int currO = 0;
        int currL = 0;
        int currF = 0;
        char prev = 'a';
        boolean isOk = true;
        for (int i = 0; i < n; ++i) {
            switch (c[i]) {
                case 'w':
                    if (prev == 'w') {
                        currW++;
                    } else if (prev == 'f' || prev == 'a') {
                        boolean localOk = false;
                        if (currW == currO && currW == currL && currW == currF) {
                            localOk = true;
                            currW = 1;
                            currO = 0;
                            currL = 0;
                            currF = 0;
                        }
                        if (!localOk) {
                            isOk = false;
                        }
                    } else {
                        isOk = false;
                    }
                    prev = 'w';
                    break;
                case 'o':
                    if (prev == 'o' || prev == 'w') {
                        currO++;
                    } else {
                        isOk = false;
                    }
                    prev = 'o';
                    break;
                case 'l':
                    if (prev == 'l' || prev == 'o') {
                        currL++;
                    } else {
                        isOk = false;
                    }
                    prev = 'l';
                    break;
                case 'f':
                    if (prev == 'l' || prev == 'f') {
                        currF++;
                    } else {
                        isOk = false;
                    }
                    prev = 'f';
                    break;
            }
        }
        if (currW == currO && currW == currL && currW == currF) {
        } else {
            isOk = false;
        }
        if (!isOk) {
            return "INVALID";
        } else {
            return "VALID";
        }
    }
}
