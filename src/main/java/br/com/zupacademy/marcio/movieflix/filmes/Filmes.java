package br.com.zupacademy.marcio.movieflix.filmes;

import javax.persistence.*;

@Entity
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Deprecated
    public Filmes() {
    }

    public Filmes(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }


}
