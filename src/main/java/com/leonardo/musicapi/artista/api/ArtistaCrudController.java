package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.service.ArtistaService;
import com.leonardo.musicapi.common.dto.ArtistaDetalheResponse;
import com.leonardo.musicapi.common.dto.ArtistaRequest;
import com.leonardo.musicapi.common.dto.ArtistaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Artistas", description = "Operações de CRUD de artistas")
@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaCrudController {

    private final ArtistaService artistaService;

    public ArtistaCrudController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }
    
    @Operation(summary = "Criar artista", description = "Cria um novo artista.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponse criar(@Valid @RequestBody ArtistaRequest request) {
        return artistaService.criar(request);
    }

    @Operation(summary = "Buscar artista por ID", description = "Retorna os detalhes de um artista específico.")
    @GetMapping("/{id}")
    public ArtistaDetalheResponse buscarPorId(@PathVariable Long id) {
        return artistaService.buscarDetalhePorId(id);
    }

    @Operation(summary = "Atualizar artista", description = "Atualiza os dados de um artista existente.")
    @PutMapping("/{id}")
    public ArtistaResponse atualizar(@PathVariable Long id, @Valid @RequestBody ArtistaRequest request) {
        return artistaService.atualizar(id, request);
    }

    @Operation(summary = "Remover artista", description = "Remove um artista existente.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        artistaService.remover(id);
    }
}
