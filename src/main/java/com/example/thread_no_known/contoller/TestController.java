package com.example.thread_no_known.contoller;


import com.example.thread_no_known.dto.DataDto;
import com.example.thread_no_known.myresp.R;
import com.example.thread_no_known.service.UserService;
import com.example.thread_no_known.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TestController extends BaseController {


    @Autowired
    private UserService userService;





    @RequestMapping("/test0")
    public Map<String,Object> test0() {
        int a = 1;
        int b = 2;
        DataDto dataDto = new DataDto();

        Map<String,Object> map = new HashMap();
        map.put("a",1);
        map.put("b",2);
        map.put("dataDto",dataDto);
        return map;
    }


    @RequestMapping("/test01")
    public Map<String,Object> test01() {
        int a = 1;
        int b = 2;
        DataDto dataDto = new DataDto();
        return R.result(a,b,dataDto);
    }



    @RequestMapping("/test02")
    public Map<String,Object> test02() {
        int a = 1;
        int b = 2;
        int c = 3;
        DataDto dataDto = new DataDto();
        return R.result(a,b,dataDto,c);
    }



    @RequestMapping("/test03")
    public DataDto test03() {
        int a = 1;
        int b = 2;
        int c = 5;
        return R.result(c,a,b);
    }




    @RequestMapping("/test")
    public DataDto test(String methodParam) {

        return userService.save();
    }



    @RequestMapping("/test2")
    public Map<String,Object> test2(String methodParam) {
        return TestUtils.resp();
    }





    @RequestMapping("/test3")
    public Map<String,Object> test3() {
        int a = 1 ;
        int b = 2;
        int c = 3;
        DataDto data = getDto();
        return R.result(a,b,c,data);
    }


    public DataDto getDto(){
        int a = 4;
        int b = 5;
        int c = 6;
        return R.result(c,b,a);
    }





    @RequestMapping("/test4")
    public Map<String,Object> test4() {
        int a = 2 ;
        int b = 2;
        int c = 3;
        return R.result(a,c);
    }



    @RequestMapping("/test5")
    public DataDto test5() {
        int a = 2 ;
        int b = 2;
        int c = 3;
        return R.result(a,c);
    }

}
