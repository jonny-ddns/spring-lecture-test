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
    
    //@GetMapping 애노테이션 ; 매핑될 문자열 설정하기
    @GetMapping("test")
    public String test(){
        //리턴; 리턴값에 해당하는 viewName을 가진 html 찾기
        return "test";
    }

    //매핑 매개값 설정 ; hello
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello hello hello");
        return "hello"; //리턴 : resources>templates> 에서 hello.html을 찾아서 매핑하기
    }

    //매핑 매개값 설정 ; hello
    //파라미터에 값을 전달하기
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("name", name);
        //리턴; hello-template.html 찾기
        return "hello-template";
    }


    /* API 방식 */
    /*
    @ResponseBody 어노테이션
    -viewResolver를 사용하지 않는다
    -대신 StringConverter가 동작함
    리턴값을 화면에 출력한다
    리턴값에 해당하는 뷰 화면을 찾지 않는다는 의미
    responseBody --> 응답 바디를 생성
    */
    //url; http://localhost:8080/hello-spring?name=spring
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloBody(@RequestParam("name") String name){
        //화면에 문자열을 전달함
        return "hello!!! "+ name;
    }

    //JSON
    //@ResponseBody 어노테이션시
    //문자열이 아닌 객체를 리턴하게 되면
    //JsonConverter가 동작하면서 json방식으로 화면에 출력된다
    //Json 이란 key-value 매핑된 형식이라고 보면된다
    //url; http://localhost:8080/hello-api?name=test
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        //Hello 객체에 값 설정하기
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @GetMapping("hello-api2")
    @ResponseBody
    //url; http://localhost:8080/hello-api2?name=test
    public String helloApi_2(@RequestParam("name") String name){
        //Hello 객체에 값 설정하기
        Hello hello = new Hello();
        hello.setName(name);
        hello.setNum(1234);
        return hello.getName()+ hello.getNum();
    }
    
    //Hello 클래스
    public static class Hello{
        private String name;
        private int num;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getNum() { return num; }
        public void setNum(int num) { this.num = num; }
    }
}
