package org.mystic.tc.srm529;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kperikov
 */
public class KingSort {
    public String[] getSortedList(String[] kings) {
        ArrayList<KingName> names = new ArrayList<KingName>();
        for (int i = 0; i < kings.length; ++i) {
            names.add(new KingName(kings[i].split(" ")[0], kings[i].split(" ")[1]));
        }
        Collections.sort(names);
        String[] ans = new String[kings.length];
        for (int i = 0; i < kings.length; ++i) {
            ans[kings.length - i - 1] = names.get(i).toString();
        }
        return ans;
    }

    class KingName implements Comparable {

        KingName(String kingName, String kingNumber) {
            this.kingName = kingName;
            this.kingNumber = parseRomanNum(kingNumber);
            this.number = kingNumber;
        }

        String kingName;
        Integer kingNumber;
        String number;

        @Override
        public int compareTo(Object o) {
            KingName k = (KingName) o;
            if (!k.kingName.equalsIgnoreCase(this.kingName)) {
                return k.kingName.compareTo(this.kingName);
            } else {
                return k.kingNumber.compareTo(this.kingNumber);
            }
        }

        @Override
        public String toString() {
            return this.kingName + " " + this.number;
        }
    }

    public static Integer parseRomanNum(String kingNumber) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        int ans = 0;
        for (int i = 0; i < kingNumber.length() - 1; ++i) {
            int a = map.get(kingNumber.charAt(i));
            int b = map.get(kingNumber.charAt(i + 1));
            if (a < b) {
                ans -= a;
            } else {
                ans += a;
            }
        }
        ans += map.get(kingNumber.charAt(kingNumber.length() - 1));
        return ans;
    }
}
