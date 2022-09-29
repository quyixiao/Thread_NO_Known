package com.example.thread_no_known.utils;

import com.example.thread_no_known.dto.DataDto;
import com.example.thread_no_known.myresp.R;

import java.util.Map;

public class TestUtils {


    public static Map<String,Object> resp(){
        int a = 1 ;
        DataDto dto = new DataDto();
        return R.result(a,dto);
    }
}
