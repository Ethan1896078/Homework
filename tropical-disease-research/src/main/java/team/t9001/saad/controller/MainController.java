package team.t9001.saad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dzkan on 2016/3/8.
 */
@Controller
@RequestMapping("/busi")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String index() {
        logger.info("!!!!!!!1");
        return "index";
    }
}