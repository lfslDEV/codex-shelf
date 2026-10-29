package com.codexshelf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    private String autor;

    @NotBlank(message = "O gênero é obrigatório")
    private String genero;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1, message = "O ano deve ser positivo")
    private Integer anoPublicacao;

    @NotNull(message = "O status de leitura é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusLeitura statusLeitura;

    public Livro() { }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public StatusLeitura getStatusLeitura() { return statusLeitura; }
    public void setStatusLeitura(StatusLeitura statusLeitura) { this.statusLeitura = statusLeitura; }
}
