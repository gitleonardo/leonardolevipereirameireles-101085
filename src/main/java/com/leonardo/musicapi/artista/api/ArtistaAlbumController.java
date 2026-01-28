package com.leonardo.musicapi.artista.api;

import com.leonardo.musicapi.artista.service.ArtistaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Relacionamentos", description = "Vínculo/desvínculo de artistas e álbuns")
@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaAlbumController {

    private final ArtistaService artistaService;

    public ArtistaAlbumController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @Operation(summary = "Vincular álbum ao artista", description = "Cria vínculo N:N entre um artista e um álbum.")
    @PostMapping("/{artistaId}/albuns/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincular(@PathVariable Long artistaId, @PathVariable Long albumId) {
        artistaService.vincularAlbum(artistaId, albumId);
    }

    @Operation(summary = "Desvincular álbum do artista", description = "Remove vínculo N:N entre um artista e um álbum.")
    @DeleteMapping("/{artistaId}/albuns/{albumId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desvincular(@PathVariable Long artistaId, @PathVariable Long albumId) {
        artistaService.desvincularAlbum(artistaId, albumId);
    }
}
