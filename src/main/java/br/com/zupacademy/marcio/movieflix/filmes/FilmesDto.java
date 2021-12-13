package br.com.zupacademy.marcio.movieflix.filmes;

import javax.validation.constraints.NotBlank;

public class FilmesDto {

    @NotBlank
    private String nome;

    @Deprecated
    public FilmesDto() {
    }

    public FilmesDto(String nome) {
        this.nome = nome;
    }

    public FilmesDto(Filmes entity){
        this.nome = entity.getNome();
    }

    public String getNome() {
        return nome;
    }

    public Filmes toModel() {
        return new Filmes(nome);
    }
}
