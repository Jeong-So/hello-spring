package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    // 웹 스프링 기초: MVC
    // 외부에서 파라미터를 받음 : @RequestParam
    // http://localhost:8080/hello-mvc?name=spring!!! : get 방식으로 name값 넘김
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);  // 파라미터로 넘어온 name을 받음
        return "hello-template.html";
    }

    // 웹 스프링 기초: API 1
    // @ResponseBody : HTTP 바디부에 직접 데이터를 넣어주겠다
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 웹 스프링 기초: API 2
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // static class 는 class안에서 class를 또 쓸 수 있음
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }


}
