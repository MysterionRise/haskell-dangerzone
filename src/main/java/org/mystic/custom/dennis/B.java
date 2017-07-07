package org.mystic.custom.dennis;

import java.util.Scanner;

public class B {

    public static void main(String[] args) throws Exception {

        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int[][] arr = new int[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                arr[i][j] = in.nextInt();
        int res = 0;
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j <= n - i; ++j) {
                for (int k = 0; k <= n - i; ++k) {
                    if (isMagic(i, j, k, arr)) {
                        System.out.println(i);
                        return;
                    }
                }
            }
        }
    }

    private static boolean isMagic(int size, int x, int y, int[][] arr) {
        int[][] a = new int[size][size];
        for (int i1 = x, i = 0; i < size; ++i1, ++i)
            for (int j1 = y, j = 0; j < size; ++j1, ++j)
                a[i][j] = arr[i1][j1];

        if (size == 1)
            return true;
        if (size == 2) {
            if (a[0][0] + a[1][1] == a[1][0] + a[0][1]) {
                return true;
            }
        }
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < size; ++i) {
            sum1 += a[i][i];
            sum2 += a[i][size - i - 1];
        }
        if (sum1 == sum2) {
            return true;
        }

        return false;
    }
}