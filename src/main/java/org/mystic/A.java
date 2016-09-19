package org.mystic;

import sun.misc.Unsafe;

import java.io.*;
import java.util.StringTokenizer;


import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class A {
    public static void main(String[] args) {
        A a =new A();
        Object o = a;
        if (a == o) {
            System.out.println(1);
        }

        if (a != o) {
            System.out.println(2);

        }
        if (a.equals(o) ){
            System.out.println(3);
        }
        if (o.equals(a)) {
            System.out.println(4);
        }

    }



}
