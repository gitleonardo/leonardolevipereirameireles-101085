package com.leonardo.musicapi.artista.service;

import com.leonardo.musicapi.album.domain.Album;
import com.leonardo.musicapi.album.repo.AlbumRepository;
import com.leonardo.musicapi.artista.domain.Artista;
import com.leonardo.musicapi.artista.repo.ArtistaRepository;
import com.leonardo.musicapi.common.dto.AlbumResumoResponse;
import com.leonardo.musicapi.common.dto.ArtistaDetalheResponse;
import com.leonardo.musicapi.common.dto.ArtistaRequest;
import com.leonardo.musicapi.common.dto.ArtistaResponse;
import com.leonardo.musicapi.common.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;

    public ArtistaService(ArtistaRepository artistaRepository, AlbumRepository albumRepository) {
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
    }

    @Transactional
    public ArtistaResponse criar(ArtistaRequest request) {
        Artista artista = new Artista(request.getNome().trim());
        Artista salvo = artistaRepository.save(artista);
        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ArtistaDetalheResponse buscarDetalhePorId(Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + id));

        List<AlbumResumoResponse> albuns = artista.getAlbuns().stream()
                .sorted(Comparator.comparing(Album::getTitulo, String.CASE_INSENSITIVE_ORDER))
                .map(a -> new AlbumResumoResponse(a.getId(), a.getTitulo()))
                .toList();

        return new ArtistaDetalheResponse(
                artista.getId(),
                artista.getNome(),
                artista.getCriadoEm(),
                artista.getAtualizadoEm(),
                albuns
        );
    }

    @Transactional
    public ArtistaResponse atualizar(Long id, ArtistaRequest request) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + id));

        artista.setNome(request.getNome().trim());
        Artista salvo = artistaRepository.save(artista);
        return toResponse(salvo);
    }

    @Transactional
    public void remover(Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + id));

        artista.getAlbuns().clear();
        artistaRepository.delete(artista);
    }

    @Transactional
    public void vincularAlbum(Long artistaId, Long albumId) {
        Artista artista = artistaRepository.findById(artistaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + artistaId));

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado: id=" + albumId));

        artista.adicionarAlbum(album);
        artistaRepository.save(artista);
    }

    @Transactional
    public void desvincularAlbum(Long artistaId, Long albumId) {
        Artista artista = artistaRepository.findById(artistaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + artistaId));

        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Álbum não encontrado: id=" + albumId));

        artista.removerAlbum(album);
        artistaRepository.save(artista);
    }

    private ArtistaResponse toResponse(Artista artista) {
        return new ArtistaResponse(
                artista.getId(),
                artista.getNome(),
                artista.getCriadoEm(),
                artista.getAtualizadoEm()
        );
    }
}
