package com.alphaz.app.controller;

import com.alphaz.core.pojo.entity.SkiingOrderEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingOrderService;
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
@RequestMapping("order")
public class SkiingOrderController {
    @Resource
    private SkiingOrderService skiingOrderService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel addEntity(@RequestBody SkiingOrderEntity skiingOrderEntity){
        ResponseModel responseModel = skiingOrderService.add(skiingOrderEntity);
        return responseModel;
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel findEntity(Long id){
        ResponseModel responseModel = skiingOrderService.find(id);
        return responseModel;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel updateEntity(@RequestBody SkiingOrderEntity skiingOrderEntity){
        ResponseModel responseModel = skiingOrderService.update(skiingOrderEntity);
        return responseModel;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel deleteEntity(Long id){
        ResponseModel responseModel = skiingOrderService.delete(id);
        return responseModel;
    }
    @RequestMapping(value = "/changeposition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel changePosition(Long id,Integer position){
        ResponseModel responseModel = skiingOrderService.changePosition(id,position);
        return responseModel;
    }
    @RequestMapping("/search")
    @ResponseBody
    public ResponseModel search(Integer pageIndex,Integer pageSize,Integer position) {
        return skiingOrderService.search(pageIndex,pageSize,position);
    }
    @RequestMapping("/findbyloginIdandposition")
    @ResponseBody
    public ResponseModel findByLoginIdAndPosition(long loginId, int position) {
        return skiingOrderService.findByLoginIdAndPosition(loginId, position);
    }

    @GetMapping("/toorderlist")
    public ModelAndView toOrderList(){
        return new ModelAndView("order/orderlist");
    }
    @GetMapping("/tocreateorder")
    public ModelAndView toCreateOrder(){
        return new ModelAndView("order/createorder");
    }
    @GetMapping("/toupdateorder")
    public ModelAndView toUpdateOrder(Long id , HttpSession session){
        session.setAttribute("orderid",id);
        return new ModelAndView("order/updateorder");
    }


}
