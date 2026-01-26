package com.leonardo.musicapi.album.service;

import com.leonardo.musicapi.album.domain.Album;
import com.leonardo.musicapi.album.repo.AlbumRepository;
import com.leonardo.musicapi.common.dto.AlbumRequest;
import com.leonardo.musicapi.common.dto.AlbumResponse;
import com.leonardo.musicapi.common.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional
    public AlbumResponse criar(AlbumRequest request) {
        Album album = new Album(request.getTitulo().trim());
        Album salvo = albumRepository.save(album);
        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public AlbumResponse buscarPorId(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado: id=" + id));
        return toResponse(album);
    }

    @Transactional
    public AlbumResponse atualizar(Long id, AlbumRequest request) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado: id=" + id));

        album.setTitulo(request.getTitulo().trim());
        Album salvo = albumRepository.save(album);
        return toResponse(salvo);
    }

    @Transactional
    public void remover(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado: id=" + id));

        // opcional: limpar relacionamento antes de deletar
        album.getArtistas().clear();

        albumRepository.delete(album);
    }

    private AlbumResponse toResponse(Album album) {
        return new AlbumResponse(
                album.getId(),
                album.getTitulo(),
                album.getCriadoEm(),
                album.getAtualizadoEm()
        );
    }
}
