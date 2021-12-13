package br.com.zupacademy.marcio.movieflix.controller;

import br.com.zupacademy.marcio.movieflix.filmes.Filmes;
import br.com.zupacademy.marcio.movieflix.filmes.FilmesDto;
import br.com.zupacademy.marcio.movieflix.filmes.FilmesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ConsultarFilmesControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    FilmesRepository filmesRepository;

   @BeforeEach
   void setUp(){
       filmesRepository.saveAll(getFilmes());
   }

   @AfterEach
   void tearDown() {
       filmesRepository.deleteAll();
   }

   @Test
   void deveRetornarUmFilmeCasoEleExista() throws Exception {

       MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get("/filmes/{nome}","Aliens")
                               .contentType(MediaType.APPLICATION_JSON);

       // Estava dando erro assim
//       MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get("/filmes/{nome}")
//                       .param("nome","Aliens")
//                               .contentType(MediaType.APPLICATION_JSON);

       FilmesDto filmesDto = new FilmesDto(getFilmes().get(0));

       String response = mapper.writeValueAsString(filmesDto);

        mockMvc.perform(chamada)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.content().json(response)
                );
   }

   @Test
   void deveRetornarErro404CasoFilmeNaoExista() throws Exception {

       MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get("/filmes/{nome}", "Aliens2")
               .contentType((MediaType.APPLICATION_JSON));

       FilmesDto filmesDto = new FilmesDto(getFilmes().get(0));

       String response = mapper.writeValueAsString(filmesDto);

       mockMvc.perform(chamada)
               .andExpect(
                       MockMvcResultMatchers.status().isNotFound()
               );
   }

   @Test
   void deveRetornarUmaListaDeFilmes() throws Exception {

       MockHttpServletRequestBuilder chamada = MockMvcRequestBuilders.get("/filmes",
               List.of(new Filmes("Aliens"), new Filmes("Em Busca da Felicidade")))
                       .contentType(MediaType.APPLICATION_JSON);

       //???????///////////// Parei aqui 23/11/2021
       
   }

    public List<Filmes> getFilmes() {
        return List.of(
        new Filmes("Aliens"),
//        new Filmes("Star Wars"),
//        new Filmes("Jornadas nas Estrelas"),
        new Filmes("Em Busca da Felicidade")
        );
    }

}



