package com.example.thread_no_known.service.impl;

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
        return R.result(a, b, c);
    }
}
