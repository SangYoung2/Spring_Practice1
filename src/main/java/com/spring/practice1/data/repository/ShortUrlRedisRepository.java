package com.spring.practice1.data.repository;


import com.spring.practice1.data.dto.ShortUrlResponseDTO;
import org.springframework.data.repository.CrudRepository;

/**
 * PackageName : com.spring.practice1.data.repository
 * FileName : ShortUrlRedisRepository
 * Author : Flature
 * Date : 2022-05-21
 * Description :
 */
public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDTO, String> {

}
