package org.mystic.codeforces.cf201_300.cf202div2;

import java.io.*;
import java.util.StringTokenizer;

public class A implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new A()).start();
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
        int[] money = new int[3];
        for (int i = 0; i < n; ++i) {
            int t = nextInt();
            if (i == 0 && t != 25) {
                out.println("NO");
                return;
            }
            int x = t - 25;
            if (x == 0) {
                money[0]++;
            } else if (x == 25) {
                if (money[0] > 0) {
                    money[0]--;
                    money[1]++;
                } else {
                    out.println("NO");
                    return;
                }
            } else if (x == 75) {
                if (money[1] >= 1 && money[0] >= 1) {
                    money[1]--;
                    money[0]--;
                    money[2]++;
                } else if (money[0] >= 3) {
                    money[0] -= 3;
                    money[2]++;
                } else {
                    out.println("NO");
                    return;
                }
            }
        }
        out.println("YES");
    }

}
