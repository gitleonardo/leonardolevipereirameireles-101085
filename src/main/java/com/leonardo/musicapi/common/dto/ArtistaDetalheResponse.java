package com.leonardo.musicapi.common.dto;

import java.time.Instant;
import java.util.List;

public class ArtistaDetalheResponse {

    private final Long id;
    private final String nome;
    private final Instant criadoEm;
    private final Instant atualizadoEm;
    private final List<AlbumResumoResponse> albuns;

    public ArtistaDetalheResponse(Long id, String nome, Instant criadoEm, Instant atualizadoEm, List<AlbumResumoResponse> albuns) {
        this.id = id;
        this.nome = nome;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.albuns = albuns;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public List<AlbumResumoResponse> getAlbuns() {
        return albuns;
    }
}
