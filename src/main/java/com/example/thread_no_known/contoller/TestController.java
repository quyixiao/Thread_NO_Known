package com.example.thread_no_known.contoller;


import com.example.thread_no_known.resp.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController extends BaseController {

    @RequestMapping("/test")
    public ResponseResult test(String methodParam) {
        try {
            Integer a = 1;
            Integer b = 2;
            Integer c = 3;
            return responseSuccess(a, b,c);
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }

}
