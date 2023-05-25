package com.spring.practice1.data.repository;

import com.spring.practice1.data.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShorUrlRepository extends JpaRepository<ShortUrl, Long> {
    ShortUrl findByUrl(String url);

    ShortUrl findByOrgUrl(String originalUrl);
}
