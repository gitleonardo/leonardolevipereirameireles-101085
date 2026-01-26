package com.leonardo.musicapi.common.dto;

import java.time.Instant;

public class ArtistaResponse {

    private final Long id;
    private final String nome;
    private final Instant criadoEm;
    private final Instant atualizadoEm;

    public ArtistaResponse(Long id, String nome, Instant criadoEm, Instant atualizadoEm) {
        this.id = id;
        this.nome = nome;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
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
}
