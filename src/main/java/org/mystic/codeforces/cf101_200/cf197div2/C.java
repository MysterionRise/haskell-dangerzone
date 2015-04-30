package org.mystic.codeforces.cf101_200.cf197div2;

import java.io.*;
import java.util.*;

/**
 * Failed solution
 *
 * @todo need to fix
 */
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
        String g = next();
        int m = nextInt();
        int count = 0;
        Set<Integer> gs = new TreeSet<Integer>();
        for (int i = 0; i < 10; ++i) {
            if (g.charAt(i) == '1') {
                gs.add(i + 1);
            }
        }
        Integer[] val = new Integer[gs.size()];
        gs.toArray(val);
        List<Integer> ans = new ArrayList<Integer>();
        int left = 0;
        int right = 0;
        int prev = -1;
        boolean flag = true;
        while (count < m && flag) {
            if (count % 2 == 0) {
                for (int i = 0; i < val.length; ++i) {
                    if (left + val[i] > right && val[i] != prev) {
                        if (count + 1 != m) {
                            if (i == val.length - 1) {
                                if (val.length >= 2 && left + val[i] < right + val[val.length - 2]) {
                                    left += val[i];
                                    prev = val[i];
                                    ans.add(val[i]);
                                    break;
                                }
                            } else {
                                if (left + val[i] < right + val[val.length - 1]) {
                                    left += val[i];
                                    prev = val[i];
                                    ans.add(val[i]);
                                    break;
                                }
                            }
                        } else {
                            left += val[i];
                            prev = val[i];
                            ans.add(val[i]);
                            break;
                        }

                    }
                }
                //put on left

            } else {
                for (int i = 0; i < val.length; ++i) {
                    if (right + val[i] > left && val[i] != prev) {
                        if (count + 1 != m) {
                            if (i == val.length - 1) {
                                if (val.length >= 2 && right + val[i] < left + val[val.length - 2]) {
                                    right += val[i];
                                    prev = val[i];
                                    ans.add(val[i]);
                                    break;
                                }
                            } else {
                                if (right + val[i] < left + val[val.length - 1]) {
                                    right += val[i];
                                    prev = val[i];
                                    ans.add(val[i]);
                                    break;
                                }
                            }
                        } else {
                            right += val[i];
                            prev = val[i];
                            ans.add(val[i]);
                            break;
                        }
                    }
                }
                // put on right
            }
            ++count;
            if (count > ans.size()) {
                flag = false;
            }
        }
        if (flag) {
            out.println("YES");
            for (int i = 0; i < ans.size(); ++i) {
                out.print(ans.get(i) + " ");
            }
        } else {
            out.println("NO");
        }
    }
}
