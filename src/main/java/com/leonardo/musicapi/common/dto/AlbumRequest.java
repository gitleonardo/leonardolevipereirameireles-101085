package com.leonardo.musicapi.common.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlbumRequest {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 2, max = 200, message = "O título deve ter entre 2 e 200 caracteres")
    private String titulo;

    public AlbumRequest() {}

    public AlbumRequest(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
