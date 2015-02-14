package org.mystic.codeforces.cf291div2;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
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
        int x0 = nextInt();
        int y0 = nextInt();
        Aircraft[] a = new Aircraft[n];
        for (int i = 0; i < n; ++i) {
            a[i] = new Aircraft(nextInt(), nextInt());
        }
        Set<Line> lines = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            lines.add(findLine(a[i].x, a[i].y, x0, y0));
        }
        out.println(lines.size());
    }

    private Line findLine(int x, int y, int x0, int y0) {
        int A = y - y0;
        int B = x0 - x;
        int C = -A * x - B * y;
        int gcd = gcd(Math.abs(C), gcd(Math.abs(A), Math.abs(B)));
        A /= gcd;
        B /= gcd;
        C /= gcd;
        if (A < 0 || (A == 0 && B < 0)) {
            A *= -1;
            B *= -1;
            C *= -1;
        }
        return new Line(A, B, C);
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private class Aircraft {
        int x;
        int y;

        private Aircraft(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Aircraft aircraft = (Aircraft) o;

            if (x != aircraft.x) return false;
            if (y != aircraft.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    private class Line {
        int A;
        int B;
        int C;

        private Line(int a, int b, int c) {
            A = a;
            B = b;
            C = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (A != line.A) return false;
            if (B != line.B) return false;
            if (C != line.C) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = A;
            result = 31 * result + B;
            result = 31 * result + C;
            return result;
        }
    }
}
