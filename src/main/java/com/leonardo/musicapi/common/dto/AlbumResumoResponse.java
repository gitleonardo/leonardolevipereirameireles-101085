package com.leonardo.musicapi.common.dto;

public class AlbumResumoResponse {

    private final Long id;
    private final String titulo;

    public AlbumResumoResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
