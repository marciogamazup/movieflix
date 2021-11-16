package br.com.zupacademy.marcio.movieflix.sessoes;

import br.com.zupacademy.marcio.movieflix.filmes.FilmesDto;
import br.com.zupacademy.marcio.movieflix.filmes.FilmesRepository;
import br.com.zupacademy.marcio.movieflix.salas.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/sessoes")
public class SessoesController {

    @Autowired
    private SessoesRepository sessoesRepository;

    @Autowired
    private FilmesRepository filmesRepository;

    @Autowired
    private SalasRepository salasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid SessoesDto dto){

        Sessoes sessoes = dto.toModel(filmesRepository, salasRepository);
        sessoesRepository.save(sessoes);

        URI location = UriComponentsBuilder.fromUriString("sessoes/{id}").buildAndExpand(sessoes.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Page<SessoesDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Sessoes> list = sessoesRepository.findAll(pageRequest);

        Page<SessoesDto> listDto = list.map(x -> new SessoesDto(x.getHorario(), x.getFilmes().getId(), x.getSalas().getId()));

        return ResponseEntity.ok().body(listDto);
    }
}
