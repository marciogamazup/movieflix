package br.com.zupacademy.marcio.movieflix.filmes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmesInexistenteException extends RuntimeException{

    public FilmesInexistenteException () {
        super("Filme inexistenten !!");
    }
}


