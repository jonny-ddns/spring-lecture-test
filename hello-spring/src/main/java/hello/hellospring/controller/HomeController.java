package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //www.google.com 처럼 도메인으로 들어가면 호출됨
    @GetMapping("/")
    //지정된 경로에서 리턴값 home 과 일치하는 웹 페이지를 찾는다.
    //있으면 home 페이지 호출, 없으면 welcome 페이지(index.html) 호출
    public String home(){ return "home"; }
}
