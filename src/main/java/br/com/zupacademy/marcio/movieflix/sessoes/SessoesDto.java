package br.com.zupacademy.marcio.movieflix.sessoes;

import br.com.zupacademy.marcio.movieflix.filmes.Filmes;
import br.com.zupacademy.marcio.movieflix.filmes.FilmesInexistenteException;
import br.com.zupacademy.marcio.movieflix.filmes.FilmesRepository;
import br.com.zupacademy.marcio.movieflix.salas.Salas;
import br.com.zupacademy.marcio.movieflix.salas.SalasInexistenteException;
import br.com.zupacademy.marcio.movieflix.salas.SalasRepository;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

public class SessoesDto {

    @NotNull
    @FutureOrPresent
    private LocalDateTime horario;

    @NotNull
    private Long filmeId;

    @NotNull
    private Long salaId;

    @Deprecated
    public SessoesDto() {
    }

    public SessoesDto(LocalDateTime horario, Long filmeId, Long salaId) {
        this.horario = horario;
        this.filmeId = filmeId;
        this.salaId = salaId;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public Long getFilmeId() {
        return filmeId;
    }

    public Long getSalaId() {
        return salaId;
    }

    public Sessoes toModel (FilmesRepository filmesRepository, SalasRepository salasRepository) {

        Optional<Filmes> filmes = Optional.ofNullable(filmesRepository.findById(filmeId).orElseThrow(FilmesInexistenteException::new));
        Optional<Salas> salas = Optional.ofNullable(salasRepository.findById(salaId).orElseThrow(SalasInexistenteException::new));

        return new Sessoes(horario,filmes.get(), salas.get());
    }
}