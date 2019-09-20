package br.com.cliente.cadastro.modelo.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.cliente.cadastro.controllers.cidade.CidadeDTO;
import br.com.cliente.cadastro.controllers.cidade.CidadePostDTO;
import br.com.cliente.cadastro.enums.Estados;
import br.com.cliente.cadastro.exceptions.EntityNotFoundException;
import br.com.cliente.cadastro.modelo.entity.Cidade;
import br.com.cliente.cadastro.modelo.repositories.CidadeRepository;

/**
 * CidadeServiceImpl
 */
@Service
class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Override
    public CidadeDTO findByNome(@NotEmpty String nome) {
        Cidade cidade = repository.findByNomeIgnoreCase(nome);

        if (cidade == null) {
            throw new EntityNotFoundException(String.format("Não foi encontrado a cidade para o nome '%s'", nome));
        }

        return returnDTO(cidade);
    }

    @Override
    public List<CidadeDTO> findByEstado(String estado) {
        String sigla = estado.toUpperCase();
        List<Cidade> cidades = repository.findByEstadoIgnoreCase(sigla);

        if (CollectionUtils.isEmpty(cidades)) {
            throw new EntityNotFoundException(String.format("Não foram encontradas cidades para o estado '%s'", sigla));
        }

        return cidades.stream().map(c -> returnDTO(c)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CidadeDTO save(CidadePostDTO dto) {
        existsEstado(dto.getEstado());

        Cidade cidade = repository.save(returnTO(dto));

        return returnDTO(cidade);
    }

    private void existsEstado(String sigla) {
        try {
            Estados.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException(
                    String.format("Não foi encontrado nenhum estado para a sigla '%s'", sigla));
        }
    }

    @Override
    public boolean existisById(Long id) {
        if (id == null || id.equals(0l)) {
            throw new IllegalArgumentException("Id inválido");
        }

        return repository.existsById(id);
    }

    private CidadeDTO returnDTO(Cidade source) {
        CidadeDTO target = new CidadeDTO();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    private Cidade returnTO(CidadePostDTO source) {
        Cidade target = new Cidade();

        BeanUtils.copyProperties(source, target);

        return target;
    }

}