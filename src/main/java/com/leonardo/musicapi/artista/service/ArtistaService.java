package com.leonardo.musicapi.artista.service;

import com.leonardo.musicapi.artista.domain.Artista;
import com.leonardo.musicapi.artista.repo.ArtistaRepository;
import com.leonardo.musicapi.common.dto.ArtistaRequest;
import com.leonardo.musicapi.common.dto.ArtistaResponse;
import com.leonardo.musicapi.common.exception.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @Transactional
    public ArtistaResponse criar(ArtistaRequest request) {
        Artista artista = new Artista(request.getNome().trim());
        Artista salvo = artistaRepository.save(artista);
        return toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ArtistaResponse buscarPorId(Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Artista não encontrado: id=" + id));
        return toResponse(artista);
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

        // opcional: limpar relacionamento antes de deletar
        artista.getAlbuns().clear();

        artistaRepository.delete(artista);
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
