package com.spring.practice1.service;

import com.spring.practice1.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;


public interface RestTemplateService {
    public String getAroundHub();

    public String getName();

    public String getName2();

    public ResponseEntity<MemberDTO> postDto();

    public ResponseEntity<MemberDTO> addHeader();

}
