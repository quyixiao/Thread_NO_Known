package com.example.thread_no_known;

import sun.text.normalizer.UBiDiProps;

public class Test2 {

    public static void main(String[] args) {
        System.out.println(getdir("com.aa.bb.cc"));
    }

    public static String  getdir(String className){
        String temps [] = className.split("\\.");
        String dir = "";
        for(int i = 0 ;i < temps.length -1 ;i ++){
            dir = dir + "/" + temps[i];
        }
        return dir;
    }
}
