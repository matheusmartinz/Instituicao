package com.example.project2.study.domain.model.Spotify;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperSpotifyService {

    @Autowired
    protected ObjectMapper objectMapper;

    protected ObjectMapper getMapper() {
        return objectMapper;
    }

}
