package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //www.google.com 처럼 도메인으로 들어가면 호출됨
    @GetMapping("/")
    //home을 찾는다. 있으면 home 페이지 호출
    //못찾으면 welcome 페이지(index.html) 호출
    public String home(){ return "home"; }
}
