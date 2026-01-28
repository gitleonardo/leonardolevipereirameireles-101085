package com.leonardo.musicapi.album.api;

import com.leonardo.musicapi.album.service.AlbumService;
import com.leonardo.musicapi.common.dto.AlbumRequest;
import com.leonardo.musicapi.common.dto.AlbumResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Álbuns", description = "Operações de CRUD de álbuns")
@RestController
@RequestMapping("/api/v1/albuns")
public class AlbumCrudController {

    private final AlbumService albumService;

    public AlbumCrudController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Operation(summary = "Criar álbum", description = "Cria um novo álbum.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumResponse criar(@Valid @RequestBody AlbumRequest request) {
        return albumService.criar(request);
    }

    @Operation(summary = "Buscar álbum por ID", description = "Retorna os detalhes de um álbum específico.")
    @GetMapping("/{id}")
    public AlbumResponse buscarPorId(@PathVariable Long id) {
        return albumService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar álbum", description = "Atualiza os dados de um álbum existente.")
    @PutMapping("/{id}")
    public AlbumResponse atualizar(@PathVariable Long id, @Valid @RequestBody AlbumRequest request) {
        return albumService.atualizar(id, request);
    }

    @Operation(summary = "Remover álbum", description = "Remove um álbum existente.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        albumService.remover(id);
    }
}
