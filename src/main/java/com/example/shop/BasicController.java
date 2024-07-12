package com.example.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //아무클래스에나 붙이면 거기서 서버 생성 가능 Spring이 알아서 챙겨줌
public class BasicController {
    @GetMapping("/") //URL 입력 ,누가 메인페이지 접속하면
    //@ResponseBody -> return 다음 글자 그대로 보내주세요 라는 뜻
    String hello(){ //return 타입은 보통 String으로 많이 생성함
        return "index.html"; //기본 경로가 static
    }

    @GetMapping("/about") //누가 이 URL로 접속하면
    @ResponseBody
    String about(){
        return "피싱사이트예요"; //이거 보내주세요
    }

}
