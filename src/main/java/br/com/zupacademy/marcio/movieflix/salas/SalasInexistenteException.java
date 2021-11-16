package br.com.zupacademy.marcio.movieflix.salas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SalasInexistenteException extends RuntimeException{

    public SalasInexistenteException () {
        super("Filme inexistenten !!");
    }
}
