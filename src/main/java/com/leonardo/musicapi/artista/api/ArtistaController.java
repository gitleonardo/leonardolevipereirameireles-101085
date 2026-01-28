package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.domain.Artista;
import com.leonardo.musicapi.artista.repo.ArtistaRepository;
import com.leonardo.musicapi.common.dto.ArtistaResumoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
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
            Pageable pageable
    ) {
        Page<Artista> page;

        if (StringUtils.hasText(nome)) {
            page = artistaRepository.findByNomeContainingIgnoreCase(nome.trim(), pageable);
        } else {
            page = artistaRepository.findAll(pageable);
        }

        return page.map(a -> new ArtistaResumoResponse(a.getId(), a.getNome()));
    }
}
