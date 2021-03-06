package com.alphaz.app.controller;

import com.alphaz.core.pojo.entity.SkiingSupportEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingSupportService;
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
@RequestMapping("support")
public class SkiingSupportController {
    @Resource
    private SkiingSupportService skiingSupportService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel addEntity(@RequestBody SkiingSupportEntity skiingSupportEntity){
        ResponseModel responseModel = skiingSupportService.add(skiingSupportEntity);
        return responseModel;
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel findEntity(Long id){
        ResponseModel responseModel = skiingSupportService.find(id);
        return responseModel;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel updateEntity(@RequestBody SkiingSupportEntity skiingSupportEntity){
        ResponseModel responseModel = skiingSupportService.update(skiingSupportEntity);
        return responseModel;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel deleteEntity(Long id){
        ResponseModel responseModel = skiingSupportService.delete(id);
        return responseModel;
    }
    @RequestMapping("/search")
    @ResponseBody
    public ResponseModel search(Integer pageIndex,Integer pageSize,String name) {
        return skiingSupportService.search(pageIndex,pageSize,name);
    }

    @GetMapping("/tosupportlist")
    public ModelAndView toSupportList(){
        return new ModelAndView("support/supportlist");
    }
    @GetMapping("/tocreatesupport")
    public ModelAndView toCreateSupport(){
        return new ModelAndView("support/createsupport");
    }
    @GetMapping("/toupdatesupport")
    public ModelAndView toUpdateSupport(Long id , HttpSession session){
        session.setAttribute("supportid",id);
        return new ModelAndView("support/updatesupport");
    }


}
