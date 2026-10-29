package com.codexshelf.repository;

import com.codexshelf.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> { }
