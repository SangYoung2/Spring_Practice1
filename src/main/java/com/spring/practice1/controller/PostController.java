package com.spring.practice1.controller;

import com.spring.practice1.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/post-api")
public class PostController {

    @PostMapping("/default")
    public String postMethod(){
        return "Hello World!";
    }

    @PostMapping("/member")
    public String PostMember(@RequestBody Map<String, Object> postDate) {
        StringBuilder sb = new StringBuilder();

        postDate.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue());
        });

        return sb.toString();
    }

    @PostMapping("/member2")
    public String PostMember2(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
    }


}
