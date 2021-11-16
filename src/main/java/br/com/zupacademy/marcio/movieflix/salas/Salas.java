package br.com.zupacademy.marcio.movieflix.salas;

import javax.persistence.*;

@Entity
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Deprecated
    public Salas() {
    }

    public Salas(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
