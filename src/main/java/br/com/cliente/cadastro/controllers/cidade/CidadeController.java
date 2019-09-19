package br.com.cliente.cadastro.controllers.cidade;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cliente.cadastro.modelo.services.CidadeService;

/**
 * CidadeController
 */
@RestController
@RequestMapping("cidades")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping(path = "/estado/{sigla}")
    public ResponseEntity<List<CidadeDTO>> findByEstado(@Valid @PathVariable("sigla") String sigla) {
        return ResponseEntity.ok(service.findByEstado(sigla));
    }

    @GetMapping(path = "/{nome}")
    public ResponseEntity<CidadeDTO> findById(@Valid @PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody CidadePostDTO dto) {
        return ResponseEntity
                .created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(service.save(dto).getId()).toUri())
                .build();
    }

}