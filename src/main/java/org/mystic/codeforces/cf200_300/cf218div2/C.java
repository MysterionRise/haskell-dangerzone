package org.mystic.codeforces.cf200_300.cf218div2;

import java.io.*;
import java.util.StringTokenizer;

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
        final char[] receipt = next().toCharArray();
        final int[] products = new int[3];
        products[0] = nextInt();
        products[1] = nextInt();
        products[2] = nextInt();
        final int[] price = new int[3];
        price[0] = nextInt();
        price[1] = nextInt();
        price[2] = nextInt();
        long money = nextLong();
        long ans = 0;
        int[] ingredients = new int[3];
        for (int i = 0; i < receipt.length; ++i) {
            switch (receipt[i]) {
                case 'B': {
                    ingredients[0]++;
                    break;
                }
                case 'S': {
                    ingredients[1]++;
                    break;
                }
                case 'C': {
                    ingredients[2]++;
                    break;
                }
            }
        }
        long burgerPrice = 0;
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; ++i) {
            burgerPrice += ingredients[i] * price[i];
            if (ingredients[i] != 0) {
                min = Math.min(products[i] / ingredients[i], min);
            }
        }
        ans += min;
        for (int i = 0; i < 3; ++i) {
            products[i] -= min * ingredients[i];
        }
        int sum = 0;
        for (int i = 0; i < 3; ++i) {
            if (ingredients[i] != 0) {
                sum += products[i];
            }
        }
        while (sum != 0) {
            long needed = 0;
            for (int i = 0; i < 3; ++i) {
                if (ingredients[i] > products[i] && ingredients[i] != 0) {
                    needed += (ingredients[i] - products[i]) * price[i];
                    products[i] += (ingredients[i] - products[i]);
                }
            }
            if (money >= needed) {
                money -= needed;
                ans++;
            } else {
                out.println(ans);
                return;
            }
            for (int i = 0; i < 3; ++i) {
                if (products[i] != 0) {
                    products[i] -= ingredients[i];
                }
            }
            sum = 0;
            for (int i = 0; i < 3; ++i) {
                if (ingredients[i] != 0) {
                    sum += products[i];
                }
            }
        }
        ans += money / burgerPrice;
        out.println(ans);
    }
}
