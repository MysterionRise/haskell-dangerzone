package org.mystic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {
        try {
            final Scanner in = new Scanner(System.in);
            final Map<Integer, String> map = new HashMap<>();
            String defaultValue = "NULL";
            while (in.hasNextLine()) {
                final String[] cmds = in.nextLine().trim().split(" ");
                switch (cmds[0]) {
                    case "PUT": {
                        if (cmds.length != 3) {
                            System.out.println("ERROR");
                        } else {
                            final int key = Integer.parseInt(cmds[1]);
                            final String value = cmds[2];
                            map.put(key, value);
                        }
                        break;
                    }
                    case "DEFAULT": {
                        if (cmds.length != 2) {
                            System.out.println("ERROR");
                        } else {
                            final String value = cmds[1];
                            defaultValue = value;
                        }
                        break;
                    }
                    case "GET": {
                        if (cmds.length != 2) {
                            System.out.println("ERROR");
                        } else {
                            final int key = Integer.parseInt(cmds[1]);
                            if (map.containsKey(key)) {
                                System.out.println(map.get(key));
                            } else {
                                System.out.println(defaultValue);
                            }
                        }
                        break;
                    }
                    default: {
                        System.out.println("ERROR");
                        break;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}