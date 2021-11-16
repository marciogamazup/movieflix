package br.com.zupacademy.marcio.movieflix.salas;

import br.com.zupacademy.marcio.movieflix.filmes.Filmes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalasRepository extends JpaRepository<Salas, Long> {
    Optional<Salas> findByNome(String nome);
}
