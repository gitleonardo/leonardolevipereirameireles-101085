package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.domain.Artista;
import com.leonardo.musicapi.artista.repo.ArtistaRepository;
import com.leonardo.musicapi.common.dto.ArtistaResumoResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Artistas", description = "Operações de listagem e busca de artistas")
@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private final ArtistaRepository artistaRepository;

    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Operation(summary = "Listar artistas", description = "Lista artistas com paginação/ordenação e filtro opcional por nome.")
    @GetMapping
public Page<ArtistaResumoResponse> listar(
        @RequestParam(name = "nome", required = false) String nome,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "sort", defaultValue = "nome,asc") String sort
) {
    // limites para evitar abuso/erro
    if (page < 0) page = 0;
    if (size < 1) size = 10;
    if (size > 100) size = 100;

    org.springframework.data.domain.Pageable pageable = criarPageable(page, size, sort);

    Page<Artista> result;
    if (org.springframework.util.StringUtils.hasText(nome)) {
        result = artistaRepository.findByNomeContainingIgnoreCase(nome.trim(), pageable);
    } else {
        result = artistaRepository.findAll(pageable);
    }

    return result.map(a -> new ArtistaResumoResponse(a.getId(), a.getNome()));
}

private org.springframework.data.domain.Pageable criarPageable(int page, int size, String sort) {
    // sort esperado: "nome,asc" ou "nome,desc"
    String[] parts = (sort == null ? "" : sort).split(",");
    String campo = (parts.length >= 1 && !parts[0].isBlank()) ? parts[0].trim() : "nome";
    String direcao = (parts.length >= 2) ? parts[1].trim().toLowerCase() : "asc";

    org.springframework.data.domain.Sort.Direction direction =
            "desc".equals(direcao) ? org.springframework.data.domain.Sort.Direction.DESC : org.springframework.data.domain.Sort.Direction.ASC;

    org.springframework.data.domain.Sort s = org.springframework.data.domain.Sort.by(direction, campo);
    return org.springframework.data.domain.PageRequest.of(page, size, s);
}
}
