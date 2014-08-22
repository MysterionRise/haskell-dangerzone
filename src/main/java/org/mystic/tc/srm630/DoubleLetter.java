package org.mystic.tc.srm630;

public class DoubleLetter
{
    public String ableToSolve(String S)
    {
        int n = S.length();
        char[] ch = S.toCharArray();
        boolean flag = true;
        int ind = 1;
        StringBuilder sb = new StringBuilder();
        while (flag) {
            if (ind <= n - 1 && ch[ind] == ch[ind - 1]) {
                for (int i = ind + 1; i < n; ++i) {
                    sb.append(ch[i]);
                }
                ch = sb.toString().toCharArray();
                sb = new StringBuilder();
                n = ch.length;
                ind = 1;
            } else if (ind < n - 1) {
                sb.append(ch[ind - 1]);
                ind++;

            } else {
                flag = false;
            }
        }
        if (ch.length == 0) {
            return "Possible";
        }
        return "Impossible";
    }

    public static void main(String[] args) {
        DoubleLetter d = new DoubleLetter();
        System.out.println(d.ableToSolve("aabccb"));
    }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!