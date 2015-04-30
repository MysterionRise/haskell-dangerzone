package org.mystic.codeforces.cf101_200.cf199div2;


import java.io.*;
import java.util.ArrayList;
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
        int[] a = new int[n];
        int[] d = new int[10];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            d[a[i]]++;
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < n / 3; ++i) {
            String tmp = "";
            if (d[1] > 0) {
                tmp += 1 + " ";
                d[1]--;
            } else {
                out.println("-1");
                return;
            }
            boolean is2 = false;
            if (d[2] > 0) {
                tmp += "2 ";
                d[2]--;
                is2 = true;
            } else if (d[3] > 0) {
                tmp += "3 ";
                d[3]--;
            } else {
                out.println("-1");
                return;
            }
            if (is2) {
                if (d[4] > 0) {
                    tmp += "4 ";
                    d[4]--;
                } else if (d[6] > 0) {
                    tmp += "6 ";
                    d[6]--;
                } else {
                    out.println("-1");
                    return;
                }
            } else {
                if (d[6] > 0) {
                    d[6]--;
                    tmp += "6 ";
                } else {
                    out.println("-1");
                    return;
                }
            }
            ans.add(tmp);
        }
        if (ans.size() != n / 3) {
            out.println("-1");
            return;
        }
        for (int i = 0; i < ans.size(); ++i) {
            out.println(ans.get(i));
        }
    }
}
