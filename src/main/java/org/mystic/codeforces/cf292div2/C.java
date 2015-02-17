package org.mystic.codeforces.cf292div2;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;

public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
    }

    public String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int[] fact = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        List<int[]> factInSimple = new ArrayList<>();
        factInSimple.add(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 1, 1, 0, 0, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 3, 1, 0, 0, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 3, 1, 0, 1, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 4, 2, 0, 1, 0, 0, 0, 0});
        factInSimple.add(new int[]{0, 0, 4, 2, 0, 1, 0, 1, 0, 0});
        factInSimple.add(new int[]{0, 0, 7, 2, 0, 1, 0, 1, 0, 0});
        factInSimple.add(new int[]{0, 0, 7, 4, 0, 1, 0, 1, 0, 0});
        int n = nextInt();
        int[] numbers = new int[n];
        final String input = next();
        char[] ch = input.toCharArray();
        for (int i = 0; i < n; ++i) {
            numbers[i] = ch[i] - '0';
        }
        int[] digits = new int[10];
        int[] dividers = new int[]{2, 3, 5, 7};
        for (int i = 0; i < n; ++i) {
            int x = fact[numbers[i]];
            for (int j = 0; j < dividers.length; ++j) {
                while (x != 1 && x % dividers[j] == 0) {
                    x /= dividers[j];
                    digits[dividers[j]]++;
                }
            }
        }
        List<Character> res = new ArrayList<>();
        for (int i = dividers.length - 1; i >= 0; --i) {
            final int digit = digits[dividers[i]];
            while (digit > 0 && check(factInSimple.get(dividers[i]), digits)) {
                res.add((char) (dividers[i] + '0'));
                final int[] ints = factInSimple.get(dividers[i]);
                for (int j = 0; j < ints.length; ++j) {
                    digits[j] -= ints[j];
                }
            }
        }
        for (int i = 9; i >= 2; --i) {
            final int[] ints = factInSimple.get(i);
            while (check(ints, digits)) {
                res.add((char) (i + '0'));
                for (int j = 0; j < ints.length; ++j) {
                    digits[j] -= ints[j];
                }
            }
        }
        Collections.sort(res);
        for (int i = res.size() - 1; i >= 0; --i) {
            out.print(res.get(i));
        }
    }

    private boolean check(int[] ints, int[] digits) {
        boolean flag = true;
        for (int j = 0; j < ints.length; ++j) {
            if (digits[j] < ints[j]) {
                flag = false;
            }
        }
        return flag;
    }
}
