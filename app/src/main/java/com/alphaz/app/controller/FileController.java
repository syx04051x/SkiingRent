package com.alphaz.app.controller;


import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.PictureDAO;
import com.alphaz.core.pojo.entity.AlphazPictureEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@RequestMapping("/file")
@Controller
public class FileController {
    @Resource
    PictureDAO pictureDAO;


    @RequestMapping("/uploadimage")
    @ResponseBody
    public ResponseModel fileUpload(HttpServletRequest request) throws IOException {
        String filepath1 = System.getProperty("user.dir") + System.getProperty("file.separator") + "uploadimg";
        File fileexist = new File(filepath1);
        if (!fileexist.exists()) {
            if (!fileexist.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                if (!fileexist.getParentFile().mkdirs()) {
                    return new ResponseModel(DataState.NAva,"文件夹创建失败！", null);
                }
            }
        }
        ResponseModel responseModel = new ResponseModel();
        responseModel.state = DataState.NAva;
        responseModel.message = "文件上传失败";
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List<AlphazPictureEntity> pictureList = new ArrayList<AlphazPictureEntity>();
        if (multipartResolver.isMultipart(request)) { //判断request是否有文件上传
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> ite = multiRequest.getFileNames();
            while (ite.hasNext()) {
                List<MultipartFile> file = multiRequest.getFiles(ite.next());
//                String finalFilepath = filepath;
                file.forEach(a -> {
                    StringBuffer name = new StringBuffer();
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
                            pictureDAO.save(picture);
                            pictureList.add(picture);

                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                });
            }
            responseModel.setState(DataState.Ava);
            responseModel.setMessage("文件上传成功");
            responseModel.setData(pictureList);//返回文件上传之后的存放地址给前台，前台再次ajax，修改表中URL字段
            return responseModel;
        }
        return responseModel;
    }


    @RequestMapping(value = "/showimg/{imgid}", method = RequestMethod.GET)
    public String IoReadImage(@PathVariable Long imgid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        AlphazPictureEntity pic = pictureDAO.findOne(imgid);
        try {
            //获取图片存放路径
            String imgPath = pic.getUrl();
            ips = new FileInputStream(new File(imgPath));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
            ips.close();
        }
        return null;
    }

    @RequestMapping(value = "/show", method = RequestMethod.DELETE)
    public String Removeimg(String path,HttpServletResponse response) {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            ips = new FileInputStream(new File(path));
            response.setContentType("multipart/form-data");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                ips.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;

    }

@RequestMapping(value = "/uploadimg", method=RequestMethod.POST)
public @ResponseBody String imgUpload(HttpServletRequest request,
                                      @RequestParam MultipartFile filedata) {

    //System.out.println("filedata --> " + filedata);
    String filedataFileName = filedata.getOriginalFilename();
    //System.out.println("filedataFileName --> " + filedataFileName);
    String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "uploadimg";
//    String path = request.getSession().getServletContext().getRealPath("uploadimg/");
    System.out.println(path);
    //UUID改文件名，避免文件名重复
    String newFileName=UUID.randomUUID().toString()+filedataFileName.substring(filedataFileName.indexOf("."),filedataFileName.length());
    String message;
    String err = "";
    String msg = path+ System.getProperty("file.separator") + newFileName;
    //System.out.println("msg--->" + msg);
    try {
        uploadFile(filedata.getBytes(), path, newFileName);
    } catch (Exception e) {
        //err = e.getMessage();
    }
    AlphazPictureEntity picture = new AlphazPictureEntity();
    picture.setState(DataState.Ava);
    picture.setName(newFileName.toString());
    picture.setUrl(path + System.getProperty("file.separator") + newFileName);
    picture=pictureDAO.save(picture);
    String temp="../file/showimg/"+picture.getId();
    message = "{\"err\":\"" + err + "\",\"msg\":\"" +temp.replace("\\","/")
            + "\"}";
    err = message;
    return message;
}

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+ System.getProperty("file.separator")+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest req){
        Map<String,Object> rs = new HashMap<String, Object>();
        MultipartHttpServletRequest mReq  =  null;
        MultipartFile file = null;
        InputStream is = null ;
        String fileName = "";
        // 原始文件名   UEDITOR创建页面元素时的alt和title属性
        String originalFileName = "";
        String filePath = "";
        try {
            mReq = (MultipartHttpServletRequest)req;
            // 从config.json中取得上传文件的ID
            file = mReq.getFile("upfile");
            // 取得文件的原始文件名称
            fileName = file.getOriginalFilename();
            originalFileName = fileName;
            /*你的处理图片的代码*/
            rs.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
//            rs.put("url",icon);         //能访问到你现在图片的路径
            rs.put("title", originalFileName);
            rs.put("original", originalFileName);

        } catch (Exception e) {
            rs.put("state", "文件上传失败!"); //在此处写上错误提示信息，这样当错误的时候就会显示此信息
            rs.put("url","");
            rs.put("title", "");
            rs.put("original", "");
        }
        return rs;
    }



}
