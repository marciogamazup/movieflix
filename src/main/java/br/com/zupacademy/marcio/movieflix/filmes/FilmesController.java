package br.com.zupacademy.marcio.movieflix.filmes;

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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("filmes")
public class FilmesController {

    @Autowired
    private FilmesRepository filmesRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid FilmesDto dto) {

        Filmes filmes = dto.toModel();
        filmesRepository.save(filmes);

        URI location = UriComponentsBuilder.fromUriString("/filmes/{id}").buildAndExpand(filmes.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<?> consultar(@PathVariable String nome) {
        if(Objects.nonNull(nome)) {
            Optional<Filmes> possívelFilme = filmesRepository.findByNome(nome);
            if(possívelFilme.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new FilmesDto(possívelFilme.get().getNome()));

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<FilmesDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Filmes> list = filmesRepository.findAll(pageRequest);

        Page<FilmesDto> listDto = list.map(x -> new FilmesDto(x.getNome()));

        return ResponseEntity.ok().body(listDto);
    }
}
