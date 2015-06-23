package org.mystic.acmp;

import java.awt.*;
import java.io.*;
import java.util.*;

public class T382 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T382()).start();
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
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final int[][] room = new int[n + 2][n + 2];
        for (int i = 0; i < room.length; ++i)
            Arrays.fill(room[i], 1);
        for (int i = 0; i < 2; ++i) {
            room[0][i] = -1;
            room[room.length - 1][room.length - i - 1] = -1;
        }
        room[1][0] = -1;
        room[room.length - 2][room.length - 1] = -1;
        for (int i = 1; i <= n; ++i) {
            final char[] input = next().toCharArray();
            for (int j = 1; j <= n; ++j) {
                if (input[j - 1] == '.') {
                    room[i][j] = 0;
                }
            }
        }
        int dx[] = {1, -1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        Stack<Point> q = new Stack<>();
        q.add(new Point(1, 1));
        q.add(new Point(n, n));
        while (!q.isEmpty()) {
            final Point p = q.pop();
            room[p.x][p.y] = 2;
            for (int k = 0; k < 4; ++k) {
                if (room[p.x + dx[k]][p.y + dy[k]] == 0) {
                    q.add(new Point(p.x + dx[k], p.y + dy[k]));
                }
            }
        }

//        final Set<View> points = new HashSet<>();
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (room[i][j] == 2 && room[i + dx[k]][j + dy[k]] == 1) {
                        res++;
//                        points.add(new View(new Point(i + dx[k], j + dy[k]), new Point(i, j)));
                    }
                }
            }
        }
//        out.println(25 * points.size());
        out.println(25 * res);
    }
}
