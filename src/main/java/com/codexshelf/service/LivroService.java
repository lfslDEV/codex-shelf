package com.codexshelf.service;

import com.codexshelf.exception.LivroNaoEncontradoException;
import com.codexshelf.model.Livro;
import com.codexshelf.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Livro cadastrar(Livro livro) {
        return repository.save(livro);
    }

    @Transactional(readOnly = true)
    public List<Livro> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Livro consultar(Long id) {
        return repository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException(id));
    }

    @Transactional
    public Livro atualizar(Long id, Livro dados) {
        Livro livro = consultar(id);
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setGenero(dados.getGenero());
        livro.setAnoPublicacao(dados.getAnoPublicacao());
        livro.setStatusLeitura(dados.getStatusLeitura());
        return repository.save(livro);
    }

    @Transactional
    public void excluir(Long id) {
        Livro livro = consultar(id);
        repository.delete(livro);
    }
}
