package com.leonardo.musicapi.album.api;

import com.leonardo.musicapi.album.domain.Album;
import com.leonardo.musicapi.album.repo.AlbumRepository;
import com.leonardo.musicapi.common.dto.AlbumResumoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
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
            Pageable pageable
    ) {
        Page<Album> page;

        if (StringUtils.hasText(titulo)) {
            page = albumRepository.findByTituloContainingIgnoreCase(titulo.trim(), pageable);
        } else {
            page = albumRepository.findAll(pageable);
        }

        return page.map(a -> new AlbumResumoResponse(a.getId(), a.getTitulo()));
    }
}
