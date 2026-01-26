package com.leonardo.musicapi.artista.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leonardo.musicapi.album.domain.Album;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "artista_album",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> albuns = new HashSet<>();

    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    protected Artista() {
        // JPA
    }

    public Artista(String nome) {
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(Set<Album> albuns) {
        this.albuns = (albuns == null) ? new HashSet<>() : albuns;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    // Helpers para manter consistência bidirecional

    public void adicionarAlbum(Album album) {
        if (album == null) return;
        this.albuns.add(album);
        album.getArtistas().add(this);
    }

    public void removerAlbum(Album album) {
        if (album == null) return;
        this.albuns.remove(album);
        album.getArtistas().remove(this);
    }

    // equals/hashCode: seguro por id quando existir

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artista other)) return false;
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
