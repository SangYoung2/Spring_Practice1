package com.spring.practice1.data.dao;

import com.spring.practice1.data.entity.ShortUrl;


public interface ShortUrlDAO {
    ShortUrl saveShortUrl(ShortUrl shortUrl);

    ShortUrl getShortUrl(String originalUrl);

    ShortUrl getOriginalUrl(String shortUrl);

    ShortUrl updateShortUrl(ShortUrl newShortUrl);

    void deleteByShortUrl(String shortUrl);

    void deleteByOriginalUrl(String originalUrl);
}
