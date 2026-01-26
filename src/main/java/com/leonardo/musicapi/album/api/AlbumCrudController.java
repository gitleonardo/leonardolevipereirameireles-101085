package com.leonardo.musicapi.album.api;

import com.leonardo.musicapi.album.service.AlbumService;
import com.leonardo.musicapi.common.dto.AlbumRequest;
import com.leonardo.musicapi.common.dto.AlbumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumCrudController {

    private final AlbumService albumService;

    public AlbumCrudController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse criar(@Valid @RequestBody AlbumRequest request) {
        return albumService.criar(request);
    }

    @GetMapping("/{id}")
    public AlbumResponse buscarPorId(@PathVariable Long id) {
        return albumService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlbumResponse atualizar(@PathVariable Long id, @Valid @RequestBody AlbumRequest request) {
        return albumService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        albumService.remover(id);
    }
}
