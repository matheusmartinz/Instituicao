package com.example.project2.study.domain.model.Spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spotify")
@RequiredArgsConstructor
public class SpotifyRestController {

    private final SpotifyService spotifyService;

    @GetMapping("/artistas")
    public ResponseEntity<List<ArtistaPopularDTO>> getArtistas() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllArtistas());
    }
}
