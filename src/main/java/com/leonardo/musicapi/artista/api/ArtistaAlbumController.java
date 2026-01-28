package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.service.ArtistaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaAlbumController {

    private final ArtistaService artistaService;

    public ArtistaAlbumController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping("/{artistaId}/albuns/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincular(@PathVariable Long artistaId, @PathVariable Long albumId) {
        artistaService.vincularAlbum(artistaId, albumId);
    }

    @DeleteMapping("/{artistaId}/albuns/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincular(@PathVariable Long artistaId, @PathVariable Long albumId) {
        artistaService.desvincularAlbum(artistaId, albumId);
    }
}
