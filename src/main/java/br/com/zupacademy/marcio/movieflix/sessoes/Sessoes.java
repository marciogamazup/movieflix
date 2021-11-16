package br.com.zupacademy.marcio.movieflix.sessoes;

import br.com.zupacademy.marcio.movieflix.filmes.Filmes;
import br.com.zupacademy.marcio.movieflix.salas.Salas;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Sessoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime horario;

    @ManyToOne
    private Filmes filmes;

    @ManyToOne
    private Salas salas;

    @Deprecated
    public Sessoes() {
    }

    public Sessoes(LocalDateTime horario, Filmes filmes, Salas salas) {
        this.horario = horario;
        this.filmes = filmes;
        this.salas = salas;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public Filmes getFilmes() {
        return filmes;
    }

    public Salas getSalas() {
        return salas;
    }

    @Override
    public String toString() {
        return "Sessoes{" +
                "id=" + id +
                ", horario=" + horario +
                ", filmes=" + filmes +
                ", salas=" + salas +
                '}';
    }
}
