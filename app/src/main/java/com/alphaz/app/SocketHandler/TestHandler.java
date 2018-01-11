package com.alphaz.app.SocketHandler;


import com.alphaz.core.pojo.viewmodel.ResponseModel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.endpoint
 * User: C0dEr
 * Date: 2017/6/29
 * Time: 上午10:55
 * Description:This is a class of com.alphaz.endpoint
 */
@Controller
public class TestHandler {
    @MessageMapping("/test")
    @SendTo("/topic/greetings")
    public ResponseModel greeting() throws Exception {
        Thread.sleep(1000);
        return new ResponseModel();
    }

}
