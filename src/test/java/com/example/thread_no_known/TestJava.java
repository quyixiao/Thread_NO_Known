package com.example.thread_no_known;

import java.util.HashMap;
import java.util.Map;

public class TestJava {


    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println(response(a, b, c));
    }

    public static Map<String, Object> response(Object... args) {
        Map<String, Object> map = new HashMap<>();
        map.put("a", args[0]);
        map.put("b", args[1]);
        map.put("c", args[2]);
        return map;
    }
}
