package com.leonardo.musicapi.common.dto;

public class ArtistaResumoResponse {

    private final Long id;
    private final String nome;

    public ArtistaResumoResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
