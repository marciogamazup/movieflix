package br.com.zupacademy.marcio.movieflix.controller;

import br.com.zupacademy.marcio.movieflix.filmes.FilmesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class FilmeControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarUmNovoFilme() throws Exception {
        // ambiente
        FilmesDto novoFilmeDto = new FilmesDto("O Retorno do Rei");
        String request = mapper.writeValueAsString(novoFilmeDto);
        // comportamento
        MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.post("/filmes").contentType(MediaType.APPLICATION_JSON).content(request);

        // verficar
        mockMvc.perform(chamada)
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                ).andExpect(
                        MockMvcResultMatchers.redirectedUrlPattern("/filmes/*")
                );
    }
}
