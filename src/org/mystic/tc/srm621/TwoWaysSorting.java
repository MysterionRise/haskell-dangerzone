package org.mystic.tc.srm621;

import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class TwoWaysSorting {
    
    public String sortingMethod(String[] s) {
        boolean isLexi = false;
        boolean isSize = false;
        int i = 1;
        for (i = 1; i < s.length && s[i - 1].length() <= s[i].length(); ++i) ;
        if (i == s.length) {
            isSize = true;
        }
        i = 1;
        for (i = 1; i < s.length && s[i - 1].compareTo(s[i]) <= 0; ++i) ;
        if (i == s.length) {
            isLexi = true;
        }
        if (isLexi == false && isSize == false) {
            return "none";
        }
        if (isLexi == true && isSize == false) {
            return "lexicographically";
        }
        if (isLexi == true && isSize == true) {
            return "both";
        }
        if (isLexi == false && isSize == true) {
            return "lengths";
        }
        return "";
    }

}