package com.alphaz.app.controller;

import com.alphaz.core.pojo.entity.SkiingProductEntity;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.service.SkiingProductService;
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
@RequestMapping("product")
public class SkiingProductController {
    @Resource
    private SkiingProductService skiingProductService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel addEntity(@RequestBody SkiingProductEntity skiingProductEntity){
        ResponseModel responseModel = skiingProductService.add(skiingProductEntity);
        return responseModel;
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel findEntity(Long id){
        ResponseModel responseModel = skiingProductService.find(id);
        return responseModel;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json",consumes="application/json")
    @ResponseBody
    public ResponseModel updateEntity(@RequestBody SkiingProductEntity skiingProductEntity){
        ResponseModel responseModel = skiingProductService.update(skiingProductEntity);
        return responseModel;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseModel deleteEntity(Long id){
        ResponseModel responseModel = skiingProductService.delete(id);
        return responseModel;
    }
    @RequestMapping("/search")
    @ResponseBody
    public ResponseModel search(Integer pageIndex,Integer pageSize,String name) {
        return skiingProductService.search(pageIndex,pageSize,name);
    }

    @GetMapping("/toproductlist")
    public ModelAndView toProductList(){
        return new ModelAndView("product/productlist");
    }
    @GetMapping("/tocreateproduct")
    public ModelAndView toCreateProduct(){
        return new ModelAndView("product/createproduct");
    }
    @GetMapping("/toupdateproduct")
    public ModelAndView toUpdateProduct(Long id , HttpSession session){
        session.setAttribute("productid",id);
        return new ModelAndView("product/updateproduct");
    }


}
