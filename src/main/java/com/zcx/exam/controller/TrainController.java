package com.zcx.exam.controller;

import com.alibaba.excel.EasyExcel;
import com.zcx.exam.common.ResultBody;
import com.zcx.exam.entity.Exam;
import com.zcx.exam.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainService trainService;

    @GetMapping()
    public String tolist(){
        return "train/list";
    }
    /**
     * 导入试题、答案、题解
     */
    @PostMapping("/import")
    @ResponseBody
    public ResultBody importTrain(HttpServletResponse response){
        ResultBody resultBody = new ResultBody();
        try {
            EasyExcel.write(response.getOutputStream(), Exam.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultBody;
    }
}
