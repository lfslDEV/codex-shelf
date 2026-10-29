package com.codexshelf;

import com.codexshelf.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LivroControllerIntegrationTest {
    @Autowired private MockMvc mvc;
    @Autowired private LivroRepository repository;

    private static final String LIVRO = """
        {"titulo":"Dom Casmurro","autor":"Machado de Assis","genero":"Romance","anoPublicacao":1899,"statusLeitura":"LIDO"}
        """;

    @BeforeEach
    void limpar() { repository.deleteAll(); }

    private String cadastrarLivro() throws Exception {
        return mvc.perform(post("/api/livros").contentType(MediaType.APPLICATION_JSON).content(LIVRO))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.titulo").value("Dom Casmurro"))
            .andReturn().getResponse().getContentAsString();
    }

    @Test
    void deveCadastrarEListar() throws Exception {
        cadastrarLivro();
        mvc.perform(get("/api/livros")).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].autor").value("Machado de Assis"));
    }

    @Test
    void deveConsultarPorId() throws Exception {
        cadastrarLivro();
        Long id = repository.findAll().get(0).getId();
        mvc.perform(get("/api/livros/{id}", id)).andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void deveAtualizar() throws Exception {
        cadastrarLivro();
        Long id = repository.findAll().get(0).getId();
        String atualizado = LIVRO.replace("Dom Casmurro", "Memórias Póstumas");
        mvc.perform(put("/api/livros/{id}", id).contentType(MediaType.APPLICATION_JSON).content(atualizado))
            .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Memórias Póstumas"));
    }

    @Test
    void deveExcluir() throws Exception {
        cadastrarLivro();
        Long id = repository.findAll().get(0).getId();
        mvc.perform(delete("/api/livros/{id}", id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/livros/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404QuandoNaoExiste() throws Exception {
        mvc.perform(get("/api/livros/9999")).andExpect(status().isNotFound())
            .andExpect(jsonPath("$.erro").exists());
    }

    @Test
    void deveValidarCamposObrigatorios() throws Exception {
        mvc.perform(post("/api/livros").contentType(MediaType.APPLICATION_JSON)
            .content(""" 
                {"titulo":"","autor":"","genero":"","anoPublicacao":0,"statusLeitura":null}
                """))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.titulo").exists());
    }
}
