package br.com.zupacademy.marcio.movieflix.filmes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmesRepository extends JpaRepository<Filmes, Long> {
    Optional<Filmes> findByNome(String nome);
}
