package org.mystic.acmp;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class T345 implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new T345()).start();
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
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void solve() throws IOException {
        final int n = nextInt();
        final Program[] programs = new Program[n];
        final Map<String, Integer> ids = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            final String name = next();
            ids.put(name, i);
            int k = nextInt();
            final String[] chain = new String[k];
            for (int j = 0; j < k; ++j) {
                chain[j] = next();
            }
            next();
            programs[i] = new Program(name, chain);
        }
        final boolean[][] graph = new boolean[n][n];
        for (Program program1 : programs) {
            int id = ids.get(program1.name);
            for (int j = 0; j < program1.chain.length; ++j) {
                int innerId = ids.get(program1.chain[j]);
                graph[id][innerId] = true;
            }
        }
        class Utils {

            int used[] = new int[n];

            boolean findCycle(int v, int fin) {
                if (used[v] == 1 && v == fin) {
                    return true;
                }
                if (used[v] == -1) {
                    used[v] = 0;
                }
                for (int i = 0; i < n; ++i) {
                    if (graph[v][i] && used[i] != 1 && i != v) {
                        used[i] = 1;
                        boolean result = findCycle(i, fin);
                        if (result)
                            return true;
                    }
                }
                return false;
            }
        }
        Utils u = new Utils();
        for (Program program : programs) {
            int id = ids.get(program.name);
            Arrays.fill(u.used, -1);
            if (graph[id][id]) {
                out.println("YES");
            } else if (u.findCycle(id, id)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private class Program {
        private final String name;
        private final String[] chain;

        public Program(String name, String[] chain) {
            this.name = name;
            this.chain = chain;
        }
    }
}
