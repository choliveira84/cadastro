package br.com.cliente.cadastro.modelo.services;

import br.com.cliente.cadastro.controllers.cliente.ClienteDTO;
import br.com.cliente.cadastro.controllers.cliente.ClientePostDTO;

/**
 * ClienteService
 */
public interface ClienteService {
    ClienteDTO findByNome(String nome);

    ClienteDTO findById(Long id);

    ClienteDTO save(ClientePostDTO dto);

    void delete(Long id);

    void update(String nome, Long id);
}