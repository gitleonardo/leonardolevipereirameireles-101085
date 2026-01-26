package com.leonardo.musicapi.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistaRequest {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 200, message = "O nome deve ter entre 2 e 200 caracteres")
    private String nome;

    public ArtistaRequest() {}

    public ArtistaRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
