package com.spring.practice1.service;

import com.spring.practice1.data.dto.ShortUrlResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ShortUrlService {
    ShortUrlResponseDTO getShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDTO generateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDTO updateShortUrl(String clientId, String clientSecret, String originalUrl);

    void deleteShortUrl(String url);
}
