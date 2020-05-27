package com.betacat.go.controller;

import com.betacat.go.config.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        Global.TURN=1;
        return "/index";
    }
}
