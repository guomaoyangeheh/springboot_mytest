package com.example.springboot_mytest.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot_mytest.bean.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Guo Mao Yang
 * @date 2019/5/7 9:00
 */
@RestController
public class FirstController {

    @GetMapping("/getMsg")
    public ResponseResult getMsg() {
        ResponseResult res = new ResponseResult();
        res.setStatus(200);
        res.setMsg("请求成功！");
        return res;
    }

    @PostMapping("/res")
    public ResponseResult res(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) JSONObject reqJsonObject) {
        String code = request.getHeader("code");
        String secret = request.getHeader("secret");
        String key = request.getHeader("key");
        String sequence = request.getHeader("sequence");
        System.out.println("code：" + code);
        System.out.println("secret: " + secret);
        System.out.println("key: " + key);
        System.out.println("sequence: " + sequence);
        String s = reqJsonObject.toJSONString();
        ResponseResult res = new ResponseResult();
        System.out.println(s);
        res.setStatus(200);
        res.setMsg("请求成功！");
        System.out.println("=================================================================/n");
        return res;
    }
}