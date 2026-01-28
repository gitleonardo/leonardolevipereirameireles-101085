package com.leonardo.musicapi.album.api;

import com.leonardo.musicapi.album.domain.Album;
import com.leonardo.musicapi.album.repo.AlbumRepository;
import com.leonardo.musicapi.common.dto.AlbumResumoResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Álbuns", description = "Operações de listagem e busca de albuns")
@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumController {

    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Operation(summary = "Listar álbuns", description = "Lista álbuns com paginação/ordenação e filtro opcional por título.")
    @GetMapping
public Page<AlbumResumoResponse> listar(
        @RequestParam(name = "titulo", required = false) String titulo,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "sort", defaultValue = "titulo,asc") String sort
) {
    if (page < 0) page = 0;
    if (size < 1) size = 10;
    if (size > 100) size = 100;

    org.springframework.data.domain.Pageable pageable = criarPageable(page, size, sort);

    Page<Album> result;
    if (org.springframework.util.StringUtils.hasText(titulo)) {
        result = albumRepository.findByTituloContainingIgnoreCase(titulo.trim(), pageable);
    } else {
        result = albumRepository.findAll(pageable);
    }

    return result.map(a -> new AlbumResumoResponse(a.getId(), a.getTitulo()));
}

private org.springframework.data.domain.Pageable criarPageable(int page, int size, String sort) {
    String[] parts = (sort == null ? "" : sort).split(",");
    String campo = (parts.length >= 1 && !parts[0].isBlank()) ? parts[0].trim() : "titulo";
    String direcao = (parts.length >= 2) ? parts[1].trim().toLowerCase() : "asc";

    org.springframework.data.domain.Sort.Direction direction =
            "desc".equals(direcao) ? org.springframework.data.domain.Sort.Direction.DESC : org.springframework.data.domain.Sort.Direction.ASC;

    org.springframework.data.domain.Sort s = org.springframework.data.domain.Sort.by(direction, campo);
    return org.springframework.data.domain.PageRequest.of(page, size, s);
}
}
