package com.example.thread_no_known.dto;

import com.example.thread_no_known.myresp.R;
import lombok.Data;


@Data
public class DataDto {
    private Integer a;
    private Integer b;
    private Integer c;


    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        DataDto dto1 = R.result(a);
        DataDto dto2 = R.result(b, a);
        DataDto dto3 = R.result(c, b, a);
    }

}
