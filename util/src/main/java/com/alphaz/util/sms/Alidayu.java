package com.alphaz.util.sms;

import com.alphaz.util.valid.ValideHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.sms
 * User: C0dEr
 * Date: 2017/3/23
 * Time: 下午3:13
 * Description:This is a class of com.alphaz.util.sms
 */
public class Alidayu {

    public static boolean sendSMS(Long userId, List<String> phone, Map<String, Object> SMSParam, String smsTemplate) {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23717613", "f581b8df521cf1e0a931c463c863aa0a");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend(userId != null ? userId + "" : "");
        req.setSmsType("normal");
        req.setSmsFreeSignName("合诚科技");
        try {
            req.setSmsParamString(new ObjectMapper().writeValueAsString(SMSParam));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        req.setRecNum(toString(phone));
        req.setSmsTemplateCode(smsTemplate);
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            return rsp.getBody().contains("alibaba_aliqin_fc_sms_num_send_response");
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return false;
    }


//    public static String getIp(HttpServletRequest request) {
//        String ip = request.getHeader("X-Real-IP");
//        if (!ValideHelper.isNullOrEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
//            return ip;
//        }
//        ip = request.getHeader("X-Forwarded-For");
//        if (!ValideHelper.isNullOrEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
//            int index = ip.indexOf(',');
//            if (index != -1) {
//                return ip.substring(0, index);
//            } else {
//                return ip;
//            }
//        } else {
//            return request.getRemoteAddr();
//        }
//
//    }

    private static String toString(List<String> phones) {
        if (ValideHelper.isNullOrEmpty(phones)) {
            return "";
        }
        return phones.stream().reduce((a, b) -> a + "," + b).get();
    }

    public static boolean sendCaptcha(String phone, String code) {
        Long userId = 71l;
        List<String> phonelist = new ArrayList<>();
        phonelist.add(phone);
        Map<String, Object> SMSParam = new HashMap<>();
        SMSParam.put("code", code);
        String smsTemplate = "SMS_57965178";
        return sendSMS(userId, phonelist, SMSParam, smsTemplate);
    }

}
