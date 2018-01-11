package com.alphaz.app.controller;


import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.service.MailService;
import com.alphaz.core.service.UserService;
import com.alphaz.util.string.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * ProjectName: IndustrialBank
 * PackageName: com.AlphaZ.controller
 * User: C0dEr
 * Date: 2017/2/13
 * Time: 下午3:44
 * Description:This is a class of com.AlphaZ.controller
 */
@Controller
@RequestMapping("/email")
public class EmailController {
    @Resource
    MailService mailService;
    @Resource
    UserService userService;
    /**
     * 发送邮箱验证码
     * @param username
     * @return
     * @throws Exception
     */
    @PostMapping("/sendemail")
    @ResponseBody
    public ResponseModel sendEmail(String username) throws  Exception{
        String email=userService.findEmailByUsername(username);
        if(email==null){
            return new ResponseModel(DataState.NAva, "用户邮箱不存在");
        }
        return mailService.sendmail(email,"忘记密码");
   }

    /**
     * 用户注册发送邮件
     * @param email
     * @return
     * @throws Exception
     */
    @PostMapping("/sendregisteremail")
    @ResponseBody
    public ResponseModel sendRegisterEmail(String email) throws  Exception{
        return mailService.sendmail(email,"用户注册");
    }
    /**
     * 发送短信验证码
     * @param phone
     * @return
     * @throws Exception
     */
    @PostMapping("/sendMessage")
    @ResponseBody
    public ResponseModel sendMessage(String phone) throws  Exception{
        ResponseModel model=new ResponseModel();
        String str = CaptchaUtil.getRandomString(4);
        String content="您的验证码："+str+"，有效期为1分钟，请尽快验证。";
        String url="http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BJR-010-00862&pwd=04C611B0ED4F7C7F436A7B832CC69BB6&mobile="+phone+"&content="+content+"&ext=&stime=&rrid=&msgfmt=";
        URL curl=new URL(url);
        sun.net.www.protocol.http.HttpURLConnection conn=(sun.net.www.protocol.http.HttpURLConnection) curl.openConnection();
        conn.setRequestMethod("GET");
        InputStream is=conn.getInputStream();
        BufferedReader buf=new BufferedReader(new InputStreamReader(is));
        String result=buf.readLine();
        model.data=str;
        model.message="成功";
        model.state = DataState.Ava;
        return model;
    }


}
