package com.wemakeprice.homework.kst.ctrl;

import com.wemakeprice.homework.kst.bean.AnalysisBean;
import com.wemakeprice.homework.kst.service.AnalysisSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainCtrl {

    @Autowired
    private AnalysisSvc analysisSvc;

    @GetMapping("/main")
    public ModelAndView main(AnalysisBean analysis){
        ModelAndView mav = new ModelAndView("main");

        if(analysis != null && !StringUtils.isEmpty(analysis.getUrl()) && !StringUtils.isEmpty(analysis.getType())){
            analysisSvc.work(analysis);
        }
        mav.addObject("model",analysis);
        return mav;
    }
}
