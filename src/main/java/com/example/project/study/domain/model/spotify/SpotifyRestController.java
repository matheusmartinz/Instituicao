package com.example.project.study.domain.model.spotify;

import com.example.project.study.api.SuperRestController;
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
public class SpotifyRestController extends SuperRestController {

    private final SpotifyService spotifyService;

    @GetMapping("/artistas")
    public ResponseEntity<List<ArtistaPopularDTO>> getArtistas() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllArtistas());
    }

    @GetMapping("/sugestoes")
    public ResponseEntity<List<SugestaoPlaylistDTO>> getSugestoes() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllSugestoesPlaylist());
    }

    @GetMapping("/links")
    public ResponseEntity<List<SpotifyLinkDTO>> getLinks() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllSpotifyLink());
    }

    @GetMapping("/playlist")
    public ResponseEntity<List<CardPlaylistDTO>> getCards() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllCardPlayList());
    }

    @GetMapping("/footerCards")
    public ResponseEntity<List<FooterCardDTO>> getFooter() throws JsonProcessingException {
        return ResponseEntity.ok(spotifyService.findAllFooterCards());
    }
}
