package com.leonardo.musicapi.album.repo;

import com.leonardo.musicapi.album.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    // Busca por título contendo (case-insensitive) + paginação/ordenação via Pageable
    Page<Album> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
