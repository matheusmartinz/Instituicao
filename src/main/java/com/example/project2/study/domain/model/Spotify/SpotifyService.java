package com.example.project2.study.domain.model.Spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService extends SuperSpotifyService {


    public List<ArtistaPopularDTO> findAllArtistas() throws JsonProcessingException {
        String contentAsString = SpotifyArtistasDataProvider.getArtistas();
        List<ArtistaPopularDTO> artistaPopularDTOS =
                getMapper().readValue(contentAsString, new TypeReference<List<ArtistaPopularDTO>>() {
        });
        return artistaPopularDTOS;
    }
}
