package org.mystic.codeforces.rockethon.year2014;


import java.io.*;
import java.util.StringTokenizer;

public class D1 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new D1()).start();
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
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
        final int n = nextInt();
        final int m = nextInt();
        final Line[] vert = new Line[n];
        final Line[] hor = new Line[m];
        for (int i = 0; i < n; ++i) {
            long x = nextLong();
            long y = nextLong();
            long l = nextLong();
            vert[i] = new Line(x, y, x, y + l);
        }
        for (int i = 0; i < m; ++i) {
            long x = nextLong();
            long y = nextLong();
            long l = nextLong();
            hor[i] = new Line(x, y, x + l, y);
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            final Line verti = vert[i];
            for (int j = 0; j < m; ++j) {
                final Line hori = hor[j];
                ans = Math.max(ans, findAns(verti, hori));
            }
        }
        out.println(ans);
    }

    private long dist(long x1, long y1, long x2, long y2) {
        if (x1 == x2) {
            return Math.abs(y1 - y2);
        }
        return Math.abs(x1 - x2);
    }

    private long findAns(Line verti, Line hori) {
        if (verti.y1 < hori.y1 && hori.y1 < verti.y2 && verti.x1 > hori.x1 && verti.x1 < hori.x2) {
            long crossX = verti.x1;
            long crossY = hori.y1;
            return Math.min(dist(crossX, crossY, hori.x1, hori.y1),
                    Math.min(dist(crossX, crossY, hori.x2, hori.y2),
                            Math.min(dist(crossX, crossY, verti.x2, verti.y2), dist(crossX, crossY, verti.x1, verti.y1))));
        } else {
            return 0;
        }
    }

    class Line {
        long x1;
        long y1;
        long x2;
        long y2;

        Line(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}
