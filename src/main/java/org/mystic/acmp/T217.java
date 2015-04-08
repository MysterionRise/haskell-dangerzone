package org.mystic.acmp;

import java.io.*;
import java.util.StringTokenizer;

public class T217 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T217()).start();
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

    class Tree {
        final int w;
        final int e;

        public Tree(int w, int e) {
            this.w = w;
            this.e = e;
        }
    }

    public void solve() throws IOException {
        final int m = nextInt();
        final Tree[] trees = new Tree[m];
        for (int i = 0; i < m; ++i) {
            trees[i] = new Tree(nextInt(), nextInt());
        }
        final int n = nextInt();
        final int[] positions = new int[n];
        for (int i = 0; i < n; ++i) {
            positions[i] = nextInt();
        }
    }
}
