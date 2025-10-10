package com.example.project.study.domain.model.spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService extends SuperSpotifyService {


    public List<ArtistaPopularDTO> findAllArtistas() throws JsonProcessingException {
        String contentAsString = SpotifyDataProvider.getArtistas();
        List<ArtistaPopularDTO> artistaPopularDTOS =
                getMapper().readValue(contentAsString, new TypeReference<List<ArtistaPopularDTO>>() {
                });
        return artistaPopularDTOS;
    }

    public List<SugestaoPlaylistDTO> findAllSugestoesPlaylist() throws JsonProcessingException {
        String contentAsString = SpotifyDataProvider.getSugestoesPlayList();
        List<SugestaoPlaylistDTO> sugestaoPlaylistDTOS =
                getMapper().readValue(contentAsString, new TypeReference<List<SugestaoPlaylistDTO>>() {
                });
        return sugestaoPlaylistDTOS;
    }

    public List<SpotifyLinkDTO> findAllSpotifyLink() throws JsonProcessingException {
        String contentAsString = SpotifyDataProvider.getSpotifyLinks();
        List<SpotifyLinkDTO> spotifyLinkDTOS =
                getMapper().readValue(contentAsString, new TypeReference<List<SpotifyLinkDTO>>() {
        });
                return spotifyLinkDTOS;
    }

    public List<CardPlaylistDTO> findAllCardPlayList() throws JsonProcessingException {
        String contentAsString = SpotifyDataProvider.getPlayListCards();
        List<CardPlaylistDTO> cardPlaylistDTOS =
                getMapper().readValue(contentAsString, new TypeReference<List<CardPlaylistDTO>>() {
                });
                return cardPlaylistDTOS;
    }
    public List<FooterCardDTO> findAllFooterCards() throws JsonProcessingException {
        String contentAsString = SpotifyDataProvider.getFooterCards();
        List<FooterCardDTO> footerCardDTOS = getMapper().readValue(contentAsString, new TypeReference<List<FooterCardDTO>>() {
        });
        return footerCardDTOS;
    }




}
