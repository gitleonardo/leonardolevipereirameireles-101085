package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.service.ArtistaService;
import com.leonardo.musicapi.common.dto.ArtistaRequest;
import com.leonardo.musicapi.common.dto.ArtistaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaCrudController {

    private final ArtistaService artistaService;

    public ArtistaCrudController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistaResponse criar(@Valid @RequestBody ArtistaRequest request) {
        return artistaService.criar(request);
    }

    @GetMapping("/{id}")
    public ArtistaResponse buscarPorId(@PathVariable Long id) {
        return artistaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ArtistaResponse atualizar(@PathVariable Long id, @Valid @RequestBody ArtistaRequest request) {
        return artistaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        artistaService.remover(id);
    }
}
