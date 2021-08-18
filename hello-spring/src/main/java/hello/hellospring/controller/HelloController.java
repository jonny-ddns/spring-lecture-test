package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /*
    ..접속 주소
    localhost:8080
    localhost:8080/hello
    localhost:8080/hello-mvc?name=apple
    localhost:8080/hello-spring?name=apple
     */

    //매핑을 hello 라고 설정
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "MAKE IT PAY");
        return "hello"; //리턴 : resources>templates> 에서 hello.html을 찾아서 매핑하기
    }

    //파라미터에 값을 전달하는 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식 --> @ResponseBody 어노테이션을 붙인다
    
    //문자를 넘겨주면 화면에 그대로 출력한다
    //StringConverter가 동작함
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloBody(@RequestParam("name") String name){
        return "hello!!! "+ name;
    }

    //객체를 넘겨주면 JSON 방식으로 리턴함
    //JsonConverter가 동작함
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    
    //클래스 생성
    public class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
