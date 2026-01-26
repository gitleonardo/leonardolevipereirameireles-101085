package com.leonardo.musicapi.artista.repo;

import com.leonardo.musicapi.artista.domain.Artista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    // Busca por nome contendo (case-insensitive) + paginação/ordenação via Pageable
    Page<Artista> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
