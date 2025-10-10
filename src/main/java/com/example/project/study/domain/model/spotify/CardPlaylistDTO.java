package com.example.project.study.domain.model.spotify;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CardPlaylistDTO {
    public String title;
    public Boolean hasBorder;
    public String titleFooter;
    public List<CardItemPlayListDTO> itens;
}
