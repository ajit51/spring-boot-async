package com.async.spring_boot_async.controller;

import com.async.spring_boot_async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/getUser")
    public String getUserMethod() {
        System.out.println("inside getUserMethod: " + Thread.currentThread().getName());
        userService.asyncMethodTest();

        return null;
    }
}
