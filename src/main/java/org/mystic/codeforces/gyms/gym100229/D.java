package org.mystic.codeforces.gyms.gym100229;

import java.io.*;
import java.util.StringTokenizer;

public class D implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new D()).start();
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
            br = new BufferedReader(new InputStreamReader(new FileInputStream("kth.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("kth.out")));
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
        int k = nextInt();
        int[] arr = new int[1743];
        for (int i = 1; i <= n; ++i) {
            arr[polynom(i)]++;
        }
        for (int i = 0; i < 1743; ++i) {
            if (arr[i] != 0) {
                if (k > arr[i]) {
                    k -= arr[i];
                } else {
                    out.println(i);
                    return;
                }
            }
        }
    }

    private int polynom(int i) {
        long x = i;
        long tmp = ((132 * (x % 1743) * (x % 1743) * (x % 1743)) % 1743 + (77 * (x % 1743) * (x % 1743)) % 1743 + (1345 * (x % 1743)) % 1743 + 1577) % 1743;
        return (int) (tmp);
    }
}
