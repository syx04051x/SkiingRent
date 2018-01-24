package com.alphaz.app.controller;

import com.alphaz.core.pojo.entity.SkiingCustomEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingCustomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.app.controller
 * User: C0dEr
 * Date: 2017/7/26
 * Time: 下午2:02
 * Description:This is a class of com.alphaz.app.controller
 */
@RestController
@RequestMapping("custom")
public class SkiingCustomController {
    @Resource
    private SkiingCustomService skiingCustomService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel addEntity(@RequestBody SkiingCustomEntity skiingCustomEntity){
        ResponseModel responseModel = skiingCustomService.add(skiingCustomEntity);
        return responseModel;
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel findEntity(Long id){
        ResponseModel responseModel = skiingCustomService.find(id);
        return responseModel;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel updateEntity(@RequestBody SkiingCustomEntity skiingCustomEntity){
        ResponseModel responseModel = skiingCustomService.update(skiingCustomEntity);
        return responseModel;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel deleteEntity(Long id){
        ResponseModel responseModel = skiingCustomService.delete(id);
        return responseModel;
    }
    @RequestMapping("/search")
    @ResponseBody
    public ResponseModel search(Integer pageIndex,Integer pageSize,String name) {
        return skiingCustomService.search(pageIndex,pageSize,name);
    }

    @GetMapping("/tocustomlist")
    public ModelAndView toCustomList(){
        return new ModelAndView("custom/customlist");
    }
    @GetMapping("/tocreatecustom")
    public ModelAndView toCreateCustom(){
        return new ModelAndView("custom/createcustom");
    }
    @GetMapping("/toupdatecustom")
    public ModelAndView toUpdateCustom(Long id , HttpSession session){
        session.setAttribute("customid",id);
        return new ModelAndView("custom/updatecustom");
    }


}
