package com.betacat.go.controller;

import com.betacat.go.algorithm.Bot;
import com.betacat.go.domain.Data;
import com.betacat.go.utils.FormatConverter;
import com.betacat.go.utils.Rule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ruin
 * @date 2020/2/22-21:22
 */
@RestController
public class DataController {

    @PostMapping("/checkerboard")
    public Data checkerboard(String data){
        int [][] board= FormatConverter.string2array(data);
        Bot bot=new Bot();
        return bot.handle(board);
    }
}
