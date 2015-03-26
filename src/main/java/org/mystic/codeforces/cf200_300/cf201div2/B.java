package org.mystic.codeforces.cf200_300.cf201div2;

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
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nextInt();
        }
        int ans = 0;
        Set<Integer> used = new HashSet<>();
        int[] is = new int[n];
        int[] pos = new int[n];
        for (int i = 0; i < n; ++i) {
            pos[arr[i]] = i;
            if (arr[i] == i) {
                is[i] = 1;
                ans++;
                used.add(i);
            }
        }
        boolean is1 = false;
        for (int i = 0; i < n; ++i) {
            if (is[i] == 0 && is[arr[i]] == 0) {
                if (pos[i] == arr[i] && pos[arr[i]] == i) {
                    out.println(ans + 2);
                    return;
                }
            }
            if (is[i] == 0 && is[pos[arr[i]]] == 0) {
                 is1 = true;
            }
        }
        if (is1) {
            out.println(ans + 1);
        } else {
            out.println(ans);
        }
    }
}
