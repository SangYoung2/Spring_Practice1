package com.spring.practice1.service.impl;

import com.spring.practice1.data.dao.ShortUrlDAO;
import com.spring.practice1.data.dto.NaverUriDTO;
import com.spring.practice1.data.dto.ShortUrlResponseDTO;
import com.spring.practice1.data.entity.ShortUrl;
import com.spring.practice1.data.repository.ShortUrlRedisRepository;
import com.spring.practice1.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

public class ShortUrlServiceImpl implements ShortUrlService {
    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final ShortUrlDAO shortUrlDAO;
    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO, ShortUrlRedisRepository shortUrlRedisRepository) {
        this.shortUrlDAO = shortUrlDAO;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    @Override
    public ShortUrlResponseDTO getShortUrl(String clientId, String clientSecret,
                                           String originalUrl) {
        LOGGER.info("[getShortUrl] request data : {}", originalUrl);

        // Cache Logic
        Optional<ShortUrlResponseDTO> foundResponseDto = shortUrlRedisRepository.findById(originalUrl);
        if (foundResponseDto.isPresent()) {
            LOGGER.info("[getShortUrl] Cache Data existed.");
            return foundResponseDto.get();
        } else {
            LOGGER.info("[getShortUrl] Cache Data does not existed.");
        }

        ShortUrl getShortUrl = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if (getShortUrl == null) {
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDTO> responseEntity = requestShortUrl(clientId, clientSecret,
                    originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
            String hash = responseEntity.getBody().getResult().getHash();

            ShortUrl shortUrlEntity = new ShortUrl();
            shortUrlEntity.setOrgUrl(orgUrl);
            shortUrlEntity.setUrl(shortUrl);
            shortUrlEntity.setHash(hash);

            shortUrlDAO.saveShortUrl(shortUrlEntity);

        } else {
            orgUrl = getShortUrl.getOrgUrl();
            shortUrl = getShortUrl.getUrl();
        }

        ShortUrlResponseDTO shortUrlResponseDto = new ShortUrlResponseDTO(orgUrl, shortUrl);

        shortUrlRedisRepository.save(shortUrlResponseDto);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDTO generateShortUrl(String clientId, String clientSecret,
                                                String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        if (originalUrl.contains("me2.do")) {
            throw new RuntimeException();
        }

        ResponseEntity<NaverUriDTO> responseEntity = requestShortUrl(clientId, clientSecret,
                originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDTO shortUrlResponseDto = new ShortUrlResponseDTO(orgUrl, shortUrl);

        // Cache Logic
        shortUrlRedisRepository.save(shortUrlResponseDto);

        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDTO updateShortUrl(String clientId, String clientSecret,
                                              String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if (url.contains("me2.do")) {
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        } else {
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }

    private ResponseEntity<NaverUriDTO> requestShortUrl(String clientId, String clientSecret,
                                                        String originalUrl) {
        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl")
                .queryParam("url", originalUrl)
                .encode()
                .build()
                .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDTO> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                entity, NaverUriDTO.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }
}
