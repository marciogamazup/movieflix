package br.com.zupacademy.marcio.movieflix.salas;

import javax.validation.constraints.NotBlank;

public class SalasDto {

    @NotBlank
    private String nome;

    @Deprecated
    public SalasDto() {
    }

    public SalasDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Salas toModel() {
        return new Salas(nome);
    }
}
