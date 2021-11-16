package br.com.zupacademy.marcio.movieflix.salas;

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
@RequestMapping("salas")
public class SalasController {

    @Autowired
    private SalasRepository salasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid SalasDto dto) {

        Salas salas = dto.toModel();
        salasRepository.save(salas);

        URI location = UriComponentsBuilder.fromUriString("/salas/{id}").buildAndExpand(salas.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<?> consultar(@PathVariable String nome) {
        if(Objects.nonNull(nome)) {
            Optional<Salas> possívelSala = salasRepository.findByNome(nome);
            if(possívelSala.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new SalasDto(possívelSala.get().getNome()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<SalasDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Salas> list = salasRepository.findAll(pageRequest);

        Page<SalasDto> listDto = list.map(x -> new SalasDto(x.getNome()));

        return ResponseEntity.ok().body(listDto);
    }
}
