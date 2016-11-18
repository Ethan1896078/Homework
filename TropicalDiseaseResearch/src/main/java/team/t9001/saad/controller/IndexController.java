package team.t9001.saad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * desc:
 * Created by huangzhe on 2016/11/18.
 */
@Controller
public class IndexController {
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("index");
    }
}
