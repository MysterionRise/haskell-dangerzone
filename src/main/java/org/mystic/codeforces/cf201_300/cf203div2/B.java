package org.mystic.codeforces.cf201_300.cf203div2;

import java.io.*;
import java.util.*;

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
        int[] type = new int[n];
        for (int i = 0; i < n; ++i) {
            type[i] = nextInt();
        }
        int[] a = new int[n];
        int[] num = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            if (a[i] != 0) {
                num[a[i] - 1]++;
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (num[i] > 1) {
                s.add(i);
            }
        }
        final int[] gr = new int[n];
        Arrays.fill(gr, -1);
        for (int i = 0; i < n; ++i) {
            if (a[i] != 0 && !s.contains(a[i] - 1) && !s.contains(i)) {
                gr[i] = a[i] - 1;
            }
        }
        final boolean[] used = new boolean[n];
        final List<Integer> ans = new ArrayList<>();
        class DFSUtils {
            public void dfs(int vertex) {
                used[vertex] = true;
                ans.add(vertex + 1);
                int to = gr[vertex];
                if (to != -1 && !used[to]) {
                    dfs(to);
                }
            }
        }
        DFSUtils utils = new DFSUtils();

        int k = Integer.MIN_VALUE;
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (type[i] == 1) {
                ans.clear();
                utils.dfs(i);
                if (ans.size() > k) {
                    k = ans.size();
                    path.clear();
                    for (int j = 0; j < k; ++j) {
                        path.add(ans.get(j));
                    }
                }
            }
        }
        out.println(k);
        for (int i = k - 1; i >= 0; --i) {
            out.print(path.get(i) + " ");
        }

    }
}
