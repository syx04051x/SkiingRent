package com.alphaz.util.string;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.string
 * User: C0dEr
 * Date: 2017/9/2
 * Time: 下午1:06
 * Description:This is a class of com.alphaz.util.string
 */
public class JsonHelper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
