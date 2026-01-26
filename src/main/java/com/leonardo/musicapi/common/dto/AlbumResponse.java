package com.leonardo.musicapi.common.dto;

import java.time.Instant;

public class AlbumResponse {

    private final Long id;
    private final String titulo;
    private final Instant criadoEm;
    private final Instant atualizadoEm;

    public AlbumResponse(Long id, String titulo, Instant criadoEm, Instant atualizadoEm) {
        this.id = id;
        this.titulo = titulo;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }
}
