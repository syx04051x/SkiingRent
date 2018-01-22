package com.alphaz.app.controller;

import com.alphaz.core.pojo.entity.SkiingEmployeeEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingEmployeeService;
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
@RequestMapping("employee")
public class SkiingEmployeeController {
    @Resource
    private SkiingEmployeeService skiingEmployeeService;

    @GetMapping(value = "/toemployeelist")
    public ModelAndView toEmployeeList() {
        return new ModelAndView("/employee/employeelist");

    }

    @GetMapping("/tocreateemployee")
    public ModelAndView toCreateSupport() {
        return new ModelAndView("employee/createemployee");
    }

    @GetMapping("/toupdateemployee")
    public ModelAndView toUpdateSupport(Long id, HttpSession session) {
        session.setAttribute("employeeid", id);
        return new ModelAndView("employee/updateemployee");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseModel addEntity(@RequestBody SkiingEmployeeEntity skiingEmployeeEntity) {
        ResponseModel responseModel = skiingEmployeeService.add(skiingEmployeeEntity);
        return responseModel;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel findEntity(Long id) {
        ResponseModel responseModel = skiingEmployeeService.find(id);
        return responseModel;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseModel updateEntity(@RequestBody SkiingEmployeeEntity skiingEmployeeEntity) {
        ResponseModel responseModel = skiingEmployeeService.update(skiingEmployeeEntity);
        return responseModel;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseModel deleteEntity(Long id) {
        ResponseModel responseModel = skiingEmployeeService.delete(id);
        return responseModel;
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseModel search(Integer pageIndex, Integer pageSize, String name) {
        return skiingEmployeeService.search(pageIndex, pageSize, name);
    }

}
