package com.example.project.study.domain.model.spotify;

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
