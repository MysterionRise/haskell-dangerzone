package org.mystic.tc.srm590;


import java.util.LinkedList;
import java.util.Queue;

public class FoxAndGo {

    int[][] component;
    boolean[][] used;
    char[][] arr;
    int[] dx1 = new int[]{0, 0, 1, -1};
    int[] dy1 = new int[]{1, -1, 0, 0};
    int n;

    public int maxKill(String[] board) {
        n = board.length;
        arr = new char[n][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[i][j] = board[i].charAt(j);
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i][j] == '.') {
                    arr[i][j] = 'x';
                    int tmp = findKilled(arr);
                    arr[i][j] = '.';
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }

    private int findKilled(char[][] arr) {
        int ans = 0;
        component = new int[n][n];
        used = new boolean[n][n];
        int cnt = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (arr[i][j] == 'o' && !used[i][j]) {
                    BFSHelper helper = new BFSHelper();
                    component[i][j] = cnt;
                    ++cnt;
                    helper.bfs(i, j);
                }
            }
        }
        int[] num = new int[cnt];
        for (int i = 1; i <= cnt; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (arr[j][k] == 'o' && component[j][k] == i) {
                        num[i - 1]++;
                    }
                }
            }
        }

        for (int i = 1; i <= cnt; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (arr[j][k] == 'o' && component[j][k] == i) {
                        for (int l = 0; l < 4; ++l) {
                            final int i1 = j + dx1[l];
                            final int i2 = k + dy1[l];
                            if (i1 >= 0 && i1 < n && i2 >= 0 && i2 < n && arr[i1][i2] == '.') {
                                //ans += num[i - 1];
                                num[i - 1] = 0;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= cnt; ++i) {
            ans += num[i - 1];
        }
        return ans;
    }

    class BFSHelper {
        public void bfs(int i, int j) {
            used[i][j] = true;
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(i, j));
            while (!q.isEmpty()) {
                Pair poll = q.poll();
                int x = poll.f;
                int y = poll.s;
                for (int k = 0; k < 4; ++k) {
                    final int i1 = x + dx1[k];
                    final int i2 = y + dy1[k];
                    if (i1 >= 0 && i1 < n && i2 >= 0 && i2 < n && !used[i1][i2] && arr[i1][i2] == 'o') {
                        q.add(new Pair(i1, i2));
                        component[i1][i2] = component[x][y];
                        used[i1][i2] = true;
                    }
                }
            }
        }
    }

    class Pair {
        int f;
        int s;

        Pair(int f, int s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (f != pair.f) return false;
            if (s != pair.s) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = f;
            result = 31 * result + s;
            return result;
        }
    }
}
