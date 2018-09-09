package org.mystic.codeforces.gyms.gym100083;

import java.io.*;
import java.util.*;

public class C1 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C1()).start();
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
//            br = new BufferedReader(new InputStreamReader(System.in));
//            out = new PrintWriter(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(new FileInputStream("bridges.in")));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("bridges.out")));
            solve();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    class Edge {
        Edge(int s, int f) {
            this.s = s;
            this.f = f;
        }

        int s;
        int f;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (f != edge.f) return false;
            if (s != edge.s) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = s;
            result = 31 * result + f;
            return result;
        }
    }


    public void solve() throws IOException {
        final int n = nextInt();
        final List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        final int m = nextInt();
        Map<Edge, Integer> edges = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            final int a = nextInt() - 1;
            final int b = nextInt() - 1;
            edges.put(new Edge(Math.min(a, b), Math.max(a, b)), i + 1);
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        final int inTime[] = new int[n];
        final int low[] = new int[n];
        final List<Edge> bridges = new ArrayList<>();
        final int[] timer = {0};

        class Helper {

            void findBridge(int u, int v) {
                inTime[v] = timer[0];
                low[v] = timer[0];
                timer[0]++;
                for (int i = 0; i < graph.get(v).size(); ++i) {
                    int w = graph.get(v).get(i);
                    if (inTime[w] == -1) {
                        findBridge(v, w);
                        low[v] = Math.min(low[v], low[w]);
                        if (low[w] == inTime[w]) {
                            bridges.add(new Edge(v, w));
                        }
                    } else {
                        if (w != u)
                            low[v] = Math.min(low[v], inTime[w]);
                    }
                }
            }
        }
        Helper h = new Helper();
        Arrays.fill(inTime, -1);
        for (int i = 0; i < n; ++i) {
            if (inTime[i] == -1) {
                h.findBridge(i, i);
            }
        }
        int size = bridges.size();
        out.println(size);
        int[] edgeNumbers = new int[size];
        for (int i = 0; i < size; ++i) {
            int a = bridges.get(i).s;
            int b = bridges.get(i).f;
            edgeNumbers[i] = edges.get(new Edge(Math.min(a, b), Math.max(a, b)));
        }
        Arrays.sort(edgeNumbers);
        for (int edgeNumber : edgeNumbers) {
            out.println(edgeNumber);
        }
    }

}
