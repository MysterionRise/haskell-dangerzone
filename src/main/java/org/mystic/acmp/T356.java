package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T356 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T356()).start();
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
//            br = new BufferedReader(new FileReader("input.txt"));
//            out = new PrintWriter(new FileWriter("output.txt"));
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    class Coin implements Comparable<Coin> {
        final int cost;
        final int weight;

        public Coin(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Coin o) {
            return 0;
        }
    }

    public void solve() throws IOException {
        final int e = nextInt();
        final int f = nextInt();
        final int n = nextInt();
        final Coin[] coins = new Coin[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = new Coin(nextInt(), nextInt());
        }


    }
}
