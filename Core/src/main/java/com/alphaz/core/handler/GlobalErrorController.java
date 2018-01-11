package com.alphaz.core.handler;

import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.constant.DataState;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.controller
 * User: C0dEr
 * Date: 2017/6/8
 * Time: 下午7:15
 * Description:This is a class of com.alphaz.controller
 */
@Controller
public class GlobalErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, produces = "text/html")
    public ModelAndView error_html(HttpServletRequest request) {
        Map<String, Object> errors = new DefaultErrorAttributes().getErrorAttributes(new ServletRequestAttributes(request), true);
        ModelAndView mav;
        switch (errors.get("status").toString()) {
            case "404":
                mav = new ModelAndView("system/404");
                break;
            case "403":
                mav = new ModelAndView("system/403");
                break;
            case "500":
                mav = new ModelAndView("system/500");
                break;
            default:
                mav = new ModelAndView("system/500");
                break;
        }
        mav.addObject("message", errors.get("message"));
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = PATH)
    public HttpEntity<ResponseModel> error(HttpServletRequest request) {
        ResponseModel<Object> model = new ResponseModel<>();
        Map<String, Object> errors = new DefaultErrorAttributes().getErrorAttributes(new ServletRequestAttributes(request), true);
        model.message = errors.get("message").toString();
        model.data = errors.get("errors");
        model.httpStatus = errors.get("status").toString();
        model.state= DataState.NAva;
        return new ResponseEntity<>(model, HttpStatus.valueOf(Integer.valueOf(model.httpStatus)));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}