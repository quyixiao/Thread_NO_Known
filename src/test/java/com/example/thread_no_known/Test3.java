package com.example.thread_no_known;

import java.util.Calendar;

public class Test3 {

    public static void main(String[] args) {
        try {
            int i = 3 / 0;

        }catch (Exception e ){
            e.printStackTrace();
        }finally {
            int j = 4;
        }
    }
}
