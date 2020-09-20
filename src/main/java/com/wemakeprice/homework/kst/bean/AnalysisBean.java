package com.wemakeprice.homework.kst.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class AnalysisBean {

    private String url;
    private String type;
    private int unit;
    private String value;
    private String left;

    private String errMsg = null;

}
