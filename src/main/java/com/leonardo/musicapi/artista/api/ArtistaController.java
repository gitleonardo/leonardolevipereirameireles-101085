package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.domain.Artista;
import com.leonardo.musicapi.artista.repo.ArtistaRepository;
import com.leonardo.musicapi.common.dto.ArtistaResumoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private final ArtistaRepository artistaRepository;

    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

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
