package org.mystic.custom.dennis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {

    public static void main(String[] args) throws Exception {

        //use the following code to fetch input from console
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        String[] line = inp.readLine().split(" ");


        int l = Integer.parseInt(line[0]);
        int r = Integer.parseInt(line[1]);

        Boolean[] isPrime = new Boolean[r + 2];

        isPrime[0] = false;
        isPrime[1] = false;
        final int length = isPrime.length;
        for (int i = 2; i < length; ++i)
            isPrime[i] = true;

        for (int i = 2; i < length; ++i) {
            if (isPrime[i]) {
                if (i * i <= length) {
                    for (int j = i * i; j < length; j += i)
                        isPrime[j] = false;
                }
            }
        }

        int res = 0;
        for (int i = l; i <= r; ++i) {
            if (isPrime[i].booleanValue() &&
                    checkIsSum(i, isPrime)) {
                res++;
            }
        }

        //Use the following code to print output
        System.out.println(res);
    }

    private static boolean checkIsSum(int n, Boolean[] isPrime) {
        int pos = 2;
        int nextPos = Arrays.asList(isPrime).subList(pos + 1, isPrime.length - 1).indexOf(true) + pos + 1;
        while (pos + nextPos + 1 < n) {
            pos = nextPos;
            nextPos = Arrays.asList(isPrime).subList(pos + 1, isPrime.length - 1).indexOf(true) + pos + 1;
        }
        if (pos + nextPos + 1 == n) {
            return true;
        }
        return false;
    }
}