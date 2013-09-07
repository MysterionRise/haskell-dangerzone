package org.mystic.codeforces.cf199div2;


import java.io.*;
import java.util.StringTokenizer;

/**
 * @author kperikov
 */
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
        int m = nextInt();
        int s = nextInt();
        int f = nextInt();
        int t[] = new int[m];
        int[] l = new int[m];
        int[] r = new int[m];
        for (int i = 0; i < m; ++i) {
            t[i] = nextInt();
            l[i] = nextInt();
            r[i] = nextInt();
        }
        StringBuilder sb = new StringBuilder();
        char dir;
        int dx;
        if (s < f) {
            dir = 'R';
            dx = 1;
        } else {
            dir = 'L';
            dx = -1;
        }
        int curr = s;
        int currT = 0;
        for (int i = 1; i <= n + m; ++i) {
            if (curr == f) {
                out.println(sb.toString());
                return;
            }
            if (currT < m && t[currT] == i) {
                if ((curr >= l[currT] && curr <= r[currT]) || (curr + dx >= l[currT] && curr + dx <= r[currT])) {
                    sb.append("X");
                } else {
                    sb.append(dir);
                    curr += dx;
                }
                ++currT;
            } else {
                sb.append(dir);
                curr += dx;
            }
        }
    }
}