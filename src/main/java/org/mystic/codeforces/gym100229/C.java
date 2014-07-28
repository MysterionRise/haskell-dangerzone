package org.mystic.codeforces.gym100229;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("right.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("right.out")));
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int m = nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; ++i) {
            numbers[i] = nextInt();
        }
        List<Pair> num = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            if (numbers[i] != numbers[i - 1]) {
                num.add(new Pair(numbers[i - 1], i - 1));
            }
        }
        num.add(new Pair(numbers[n - 1], n - 1));
        for (int i = 0; i < m; ++i) {
            int x = nextInt();
            out.println(findRightMostPos(num, x) + 1);
        }
    }

    private int findRightMostPos(List<Pair> numbers, int x) {
        int v = binFind(0, numbers.size() - 1, numbers, x);
        return v;
    }

    private int binFind(int l, int r, List<Pair> numbers, int x) {
        if (l > r) {
            return -1;
        }
        int mid = (l + r) / 2;
        if (numbers.get(mid).first == x) {
            return numbers.get(mid).second;
        } else if (numbers.get(mid).first > x) {
            return binFind(l, mid - 1, numbers, x);
        } else {
            return binFind(mid + 1, r, numbers, x);
        }
    }

    class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != pair.first) return false;
            if (second != pair.second) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
}
