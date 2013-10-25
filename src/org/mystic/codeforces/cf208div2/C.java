package org.mystic.codeforces.cf208div2;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// @todo failed solution, WA12
public class C implements Runnable {

    PrintWriter out;
    BufferedReader br;
    StringTokenizer st;

    public static void main(String[] args) throws IOException {
        new Thread(new C()).start();
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
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>();
        ArrayList<Pair<Integer, Integer>> numbers = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            a[i] = nextInt();
            if (a[i] == 0) {
                if (pq.isEmpty()) {
                    out.println("0");
                } else {
                    int k = 0;
                    ArrayList<Pair<Integer, Integer>> r = new ArrayList<>();
                    while (k < 3 && !pq.isEmpty()) {
                        r.add(pq.poll());
                        k++;
                    }
                    int left = r.size();
                    for (int j = 0; j < numbers.size(); ++j) {
                        boolean found = false;
                        for (int l = 0; l < r.size(); ++l) {
                            if (r.get(l).second == numbers.get(j).second) {
                                found = true;
                            }
                        }
                        if (!found) {
                            out.println("pushBack");
                        } else {
                            if (left == 3) {
                                out.println("pushQueue");
                            } else if (left == 2) {
                                out.println("pushFront");
                            } else if (left == 1) {
                                out.println("pushStack");
                            } else {
                                out.println("pushBack");
                            }
                            left--;
                        }
                    }
                    out.print(r.size() + " ");
                    if (r.size() == 3) {
                        out.println("popStack popFront popQueue");
                    } else if (r.size() == 2) {
                        out.println("popStack popFront");
                    } else {
                        out.println("popStack");
                    }
                }
                pq.clear();
                numbers.clear();
            } else {
                pq.add(new Pair<>(a[i], i));
                numbers.add(new Pair<>(a[i], i));
            }
        }
        if (numbers.size() != 0) {
            for (int i = 0; i < numbers.size(); ++i) {
                out.println("pushBack");
            }
        }
    }

    class Pair<T extends Comparable, F> implements Comparable {

        T first;
        F second;

        Pair(T first, F second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Object o) {
            Pair pair = (Pair) o;
            return pair.first.compareTo(this.first);
        }
    }
}
