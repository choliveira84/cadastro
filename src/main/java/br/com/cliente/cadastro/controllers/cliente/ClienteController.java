package br.com.cliente.cadastro.controllers.cliente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cliente.cadastro.modelo.services.ClienteService;

/**
 * ClienteController
 */
@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ClienteDTO> findById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<ClienteDTO> findById(@Valid @PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody ClientePostDTO dto) {
        return ResponseEntity
                .created(UriComponentsBuilder.fromPath("/{id}").buildAndExpand(service.save(dto).getId()).toUri())
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ClientePatchDTO dto) {
        return ResponseEntity.ok(service.update(dto, id));
    }
}