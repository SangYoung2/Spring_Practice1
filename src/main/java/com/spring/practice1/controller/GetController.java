package com.spring.practice1.controller;

import com.spring.practice1.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/get-api")
public class GetController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/name")
    public String getName(){
        return "Flature";
    }

    @GetMapping("/variable1/{variable}")
    public String getVariable(@PathVariable String variable) {
        // url 의 {variable}의 값을 변수로 등록하여 {variable}의 값을 가져온다.
        return variable;
    }

    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        // @PathVariable()에서 ()안에 {variable}를 넣음으로써 지정을 하고 다른 이름의 변수명으로 지정하여 사용한다.
        return var;
    }

    @GetMapping("/request1")
    public String getRequestParam1(
        // 파라미터의 key값을 알고 있을 경우 @RequestParam 어노테이션을 통해 key값을 입력해주는 것으로 value값을 받아온다.        
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String organization
    )
    {
        return name + " " + email + " " + organization;
    }

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        // 파라미터 값이 어떠한 것이 올지 모를경우 Map으로 지정하여 반복문을 통하여 값을 출력
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDTO memberDTO){
        // DTO 객체를 생성하여 파라미터 값을 DTO에 저장하여 DTO객체로 값을 출력함 (DTO안에 객체들과 이름이 같아야만 값이 저장된다.)
        return memberDTO.toString();
    }
}
