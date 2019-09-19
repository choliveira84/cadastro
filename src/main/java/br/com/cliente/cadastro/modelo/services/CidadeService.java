package br.com.cliente.cadastro.modelo.services;

import java.util.List;

import br.com.cliente.cadastro.controllers.cidade.CidadeDTO;
import br.com.cliente.cadastro.controllers.cidade.CidadePostDTO;

/**
 * CidadeService
 */
public interface CidadeService {

    CidadeDTO findByNome(String nome);

    List<CidadeDTO> findByEstado(String estado);

    CidadeDTO save(CidadePostDTO dto);
}