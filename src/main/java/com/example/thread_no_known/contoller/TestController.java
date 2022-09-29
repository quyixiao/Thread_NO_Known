package com.example.thread_no_known.contoller;


import com.example.thread_no_known.dto.DataDto;
import com.example.thread_no_known.myresp.R;
import com.example.thread_no_known.service.UserService;
import com.example.thread_no_known.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController extends BaseController {


    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public DataDto test(String methodParam) {

        return userService.save();
    }



    @RequestMapping("/test2")
    public Map<String,Object> test2(String methodParam) {
        return TestUtils.resp();
    }


}
