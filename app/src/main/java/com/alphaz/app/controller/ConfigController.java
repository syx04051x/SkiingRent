package com.alphaz.app.controller;


import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.PictureDAO;
import com.alphaz.core.pojo.entity.AlphazPictureEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("/config")
@Controller
public class ConfigController {
    @Resource
    PictureDAO pictureDAO;
    @RequestMapping("/uploadimage")
    @ResponseBody
    public Map<String,Object> fileUpload(HttpServletRequest request) throws IOException {
        Map<String,Object> rs = new HashMap<String, Object>();
        String filepath1 = System.getProperty("user.dir") + System.getProperty("file.separator") + "uploadimg";
        File fileexist = new File(filepath1);
        if (!fileexist.exists()) {
            if (!fileexist.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                if (!fileexist.getParentFile().mkdirs()) {
                    rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
                    rs.put("url","");
                    rs.put("title", "");
                    rs.put("original", "");
                    return rs;
                }
            }
        }
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List<AlphazPictureEntity> pictureList = new ArrayList<AlphazPictureEntity>();
        StringBuffer name = new StringBuffer();
        final AlphazPictureEntity[] result = {new AlphazPictureEntity()};
        if (multipartResolver.isMultipart(request)) { //判断request是否有文件上传
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> ite = multiRequest.getFileNames();
            while (ite.hasNext()) {
                List<MultipartFile> file = multiRequest.getFiles(ite.next());
//                String finalFilepath = filepath;
                file.forEach(a -> {

                    name.append(a.getOriginalFilename());
                    name.insert(a.getOriginalFilename().indexOf("."), "_" + System.nanoTime());
                    if (a != null) {
                        File localFile = new File(filepath1 + System.getProperty("file.separator") + name);//服务器存放地址，部署时要改
                        try {
                            a.transferTo(localFile); //将上传文件写到服务器上指定的文件
                            AlphazPictureEntity picture = new AlphazPictureEntity();
                            picture.setState(DataState.Ava);
                            picture.setName(name.toString());
                            picture.setUrl(filepath1 + System.getProperty("file.separator") + name);
                            result[0] = pictureDAO.save(picture);


                        } catch (IllegalStateException e) {
                            rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息

                            e.printStackTrace();
                        } catch (IOException e) {
                            rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
                            e.printStackTrace();
                        }
                    }
                });
            }
            rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
            rs.put("url","/file/showimg/"+ result[0].getId()+"");         //能访问到你现在图片的路径
            rs.put("title", name);
            rs.put("original", name);
            return rs;
        }
        return rs;
    }



    @RequestMapping("/WeChatFile")
    @ResponseBody
    public static File uploadFile() throws Exception {
        String filepath1 = System.getProperty("user.dir") + System.getProperty("file.separator");

        //            is.setEncoding("utf-8");
        File f = new File(filepath1 + System.getProperty("file.separator") +"MP_verify_uqDPUOIcDKJCl0AJ.txt");

        return f;
    }


}
