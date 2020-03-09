package com.wlst.cloud.controller;

import com.wlst.cloud.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class WxController {



    @RequestMapping("/wx")
    @ResponseBody
    public void wxCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //校验请求
        if(WxService.check(timestamp,nonce,signature)) {
            System.out.println("接入成功");
            PrintWriter pw = response.getWriter();
            //原样返回echostr参数
            pw.println(echostr);
            pw.flush();
            pw.close();
        }else {
            System.out.println("接入失败");
        }

        System.out.println("qwq");
    }


}
