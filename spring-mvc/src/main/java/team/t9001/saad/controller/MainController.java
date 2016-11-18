package team.t9001.saad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
@RequestMapping("/busi")
public class MainController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}