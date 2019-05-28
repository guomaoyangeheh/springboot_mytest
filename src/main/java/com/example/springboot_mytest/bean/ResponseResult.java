package com.example.springboot_mytest.bean;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Guo Mao Yang
 * @date 2019/5/7 9:07
 */
@Data
public class ResponseResult {

    public ResponseResult() {
    }

    public ResponseResult(int status) {
        this.status = status;
    }

    private int status;
    private String msg;
}
