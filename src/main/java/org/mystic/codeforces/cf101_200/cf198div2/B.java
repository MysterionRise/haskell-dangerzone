package org.mystic.codeforces.cf101_200.cf198div2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new B()).start();
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
        int n = nextInt();
        List<Pair<Integer, Integer>> x = new ArrayList<>();
        List<Pair<Integer, Integer>> y = new ArrayList<>();
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; ++i) {
            x.add(new Pair<>(nextInt(), i));
            xs[i] = x.get(i).first;
            y.add(new Pair<>(nextInt(), i));
            ys[i] = y.get(i).first;
        }
        Collections.sort(x);
        Collections.sort(y);
        int xMin = x.get(0).first;
        int xMax = x.get(n - 1).first;
        int xMid = (xMin + xMax) / 2;
        int yMin = y.get(0).first;
        int yMax = y.get(n - 1).first;
        int yMid = (yMin + yMax) / 2;
        int num1 = -1, num2 = -1, num3 = -1, num4 = -1;
        double d1 = -100.0d;
        double d2 = -100.0d;
        double d3 = -100.0d;
        double d4 = -100.0d;
        for (int i = xMin; i <= xMid; ++i) {
            for (int j = 0; j < n; ++j) {
                if (xs[j] == i && ys[j] <= yMid) {
                    if (dist(xs[j], ys[j]) > d1) {
                        d1 = dist(xs[j], ys[j]);
                        num1 = j;
                    }
                }
            }
        }
        for (int i = xMin; i <= xMid; ++i) {
            for (int j = 0; j < n; ++j) {
                if (xs[j] == i && ys[j] >= yMid) {
                    if (dist(xs[j], ys[j]) > d2) {
                        d2 = dist(xs[j], ys[j]);
                        num2 = j;
                    }
                }
            }
        }
        for (int i = xMid; i <= xMax; ++i) {
            for (int j = 0; j < n; ++j) {
                if (xs[j] == i && ys[j] >= yMid) {
                    if (dist(xs[j], ys[j]) > d3) {
                        d3 = dist(xs[j], ys[j]);
                        num3 = j;
                    }
                }
            }
        }
        for (int i = xMid; i <= xMax; ++i) {
            for (int j = 0; j < n; ++j) {
                if (xs[j] == i && ys[j] <= yMid) {
                    if (dist(xs[j], ys[j]) > d4) {
                        d4 = dist(xs[j], ys[j]);
                        num4 = j;
                    }
                }
            }
        }

        out.println(calsSq(xs[num1], ys[num1],
                xs[num2], ys[num2],
                xs[num3], ys[num3],
                xs[num4], ys[num4]));

    }

    private double dist(int x, int y) {
        return x * x + y * y;
    }

    private double calsSq(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        double S = 0.0d;
        S += (x2 - x1) * (y1 + y2) / 2.0d;
        S += (x3 - x2) * (y2 + y3) / 2.0d;
        S += (x3 - x4) * (y3 + y4) / 2.0d;
        S += (x4 - x1) * (y4 + y1) / 2.0d;
        return S;
    }

    class Pair<T, F> implements Comparable {

        T first;
        F second;

        Pair(T first, F second) {
            this.first = first;
            this.second = second;
        }

        T getFirst() {
            return first;
        }

        void setFirst(T first) {
            this.first = first;
        }

        F getSecond() {
            return second;
        }

        void setSecond(F second) {
            this.second = second;
        }

        @Override
        public int compareTo(Object o) {
            Pair<Integer, Integer> pair = (Pair<Integer, Integer>) o;
            if (((Integer) this.first) > pair.first) {
                return 1;
            } else if (this.first == pair.first) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
