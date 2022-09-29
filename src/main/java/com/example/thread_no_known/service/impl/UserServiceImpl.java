package com.example.thread_no_known.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.thread_no_known.dto.DataDto;
import com.example.thread_no_known.myresp.R;
import com.example.thread_no_known.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public DataDto save() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;

        DataDto dto1 = R.result(a);
        DataDto dto2 = R.result(b, a);
        DataDto dto3 = R.result(c, b, a);

        System.out.println(JSON.toJSONString(dto1));
        System.out.println(JSON.toJSONString(dto2));
        System.out.println(JSON.toJSONString(dto3));


        return R.result(c,a,b);
    }
















}
