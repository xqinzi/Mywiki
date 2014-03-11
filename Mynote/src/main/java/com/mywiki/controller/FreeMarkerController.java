package com.mywiki.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 * FreeMarker示例控制器
 */
@Controller
@RequestMapping("/freeMarker")
public class FreeMarkerController {
	@RequestMapping("/hello")
    public String sayHello(ModelMap map) {
        System.out.println("say Hello ……");
        map.addAttribute("name", " World!");
        return "/hello.ftl";
    }
    
    @RequestMapping("/hi")
    public String sayHi(ModelMap map) {
        System.out.println("say hi ……");
        map.put("name", "jojo");
        return "/hi.ftl";
    }
}
