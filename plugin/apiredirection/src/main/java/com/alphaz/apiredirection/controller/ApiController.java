package com.alphaz.apiredirection.controller;


import com.alphaz.util.valid.ValideHelper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by C0dEr on 2017/9/2.
 */
@RequestMapping("/api/**")
@RestController
public class ApiController {
    @Value("${xiaoshouyi.redirection.uri:https://api.xiaoshouyi.com}")
    private String host;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, ExecutionException, InterruptedException {
        String result = compile(request, response);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
    }


    private Map<String, String[]> distinct(String param, Map<String, String[]> paramMap) {
        Map<String, String[]> newMap = new HashMap<>();
        Map<String, String> queryParam = new HashMap<>();

        if (!ValideHelper.isNullOrEmpty(param)) {
            String[] params = param.split("&");
            Arrays.stream(params).forEach(a -> {
                String[] p = a.split("=");
                queryParam.put(p[0], p[1]);
            });
        }
        for (Map.Entry<String, String[]> item : paramMap.entrySet()) {
            if (queryParam.get(item.getKey()) == null) {
                newMap.put(item.getKey(), item.getValue());
            }
        }
        for (Map.Entry<String, String> item : queryParam.entrySet()) {
            if (paramMap.get(item.getKey()) != null) {
                List<String> values = new ArrayList<>();
                for (String str : paramMap.get(item.getKey())) {
                    if (!str.equals(item.getValue())) {
                        values.add(str);
                    }
                }
                if (values.size() > 0) {
                    newMap.put(item.getKey(), values.toArray(new String[values.size()]));
                }
            }
        }
        return newMap;
    }

    private String compile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Request.Builder builder = new Request.Builder();
        if (!ValideHelper.isNullOrEmpty(request.getHeader("Content-Type"))) {
            builder.addHeader("Content-Type", request.getHeader("Content-Type"));
        }
        if (!ValideHelper.isNullOrEmpty(request.getHeader("Authorization"))) {
            builder.addHeader("Authorization", request.getHeader("Authorization"));
        }
        String url = "";
        if (!ValideHelper.isNullOrEmpty(request.getQueryString())) {
            url = host + request.getRequestURI().replace("/api", "") + "?" + request.getQueryString();
        } else {
            url = host + request.getRequestURI().replace("/api", "");
        }
        builder.url(url);
        RequestBody requestBody = null;
        Map<String, String[]> param = distinct(request.getQueryString(), request.getParameterMap());
        if (request.getContentType() == null || "x-www-form-urlencoded".equals(MediaType.parse(request.getContentType()).subtype())) {
            FormBody.Builder formBuilder = new FormBody.Builder();
            for (Map.Entry<String, String[]> map : param.entrySet()) {
                formBuilder.addEncoded(map.getKey(), map.getValue()[0]);
            }
            requestBody = formBuilder.build();
        } else {
            InputStream is = request.getInputStream();
            BufferedReader bufferedReader = null;
            StringBuilder stringBuilder = new StringBuilder();
            if (is != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(is));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
                requestBody = RequestBody.create(MediaType.parse(request.getContentType()), stringBuilder.toString());
            }

        }
        if ("GET".equals(request.getMethod())) {
            requestBody = null;
        }
        builder.method(request.getMethod(), requestBody);
        OkHttpClient client = new OkHttpClient();
        Response innerResponse = client.newCall(builder.build()).execute();
        return innerResponse.body().string();

    }
}