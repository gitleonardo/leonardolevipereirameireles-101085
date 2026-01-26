package com.leonardo.musicapi.album.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leonardo.musicapi.artista.domain.Artista;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @JsonIgnore
    @ManyToMany(mappedBy = "albuns")
    private Set<Artista> artistas = new HashSet<>();

    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    protected Album() {
        // JPA
    }

    public Album(String titulo) {
        this.titulo = titulo;
    }

    @PrePersist
    void prePersist() {
        Instant now = Instant.now();
        this.criadoEm = now;
        this.atualizadoEm = now;
    }

    @PreUpdate
    void preUpdate() {
        this.atualizadoEm = Instant.now();
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(Set<Artista> artistas) {
        this.artistas = (artistas == null) ? new HashSet<>() : artistas;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    // equals/hashCode: seguro por id quando existir

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album other)) return false;
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
