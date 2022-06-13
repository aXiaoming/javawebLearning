package com.atguigu.myssm.myspringmvc;

public class PageController {

    //  http://localhost:8080/pro22/page.do?operate=page&page=login
    public String page(String page){
        return page ;       // 从controller传过来的参数是：frames/left
    }
}
