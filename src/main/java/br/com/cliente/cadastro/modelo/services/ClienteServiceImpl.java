package br.com.cliente.cadastro.modelo.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cliente.cadastro.controllers.cliente.ClienteDTO;
import br.com.cliente.cadastro.controllers.cliente.ClientePostDTO;
import br.com.cliente.cadastro.modelo.entity.Cliente;
import br.com.cliente.cadastro.modelo.repositories.ClienteRepository;

/**
 * ClienteServiceImpl
 */
@Service
class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public ClienteDTO findByNome(String nome) {
        Cliente cliente = repository.findByNome(nome);

        if (cliente == null) {
            throw new EntityNotFoundException(String.format("Não foi encontrado o cliente para o nome '%s'", nome));
        }

        return returnDTO(cliente);
    }

    @Override
    public ClienteDTO findById(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Não foi encontrado o cliente para o id '%d'", id)));

        return returnDTO(cliente);
    }

    @Override
    public ClienteDTO save(ClientePostDTO dto) {
        Cliente cliente = repository.save(returnTO(dto));
        return returnDTO(cliente);
    }

    @Override
    public void update(String nome, Long id) {
        ClienteDTO dto = findById(id);
        Cliente cliente = returnTO(dto);

        cliente.setNome(nome);

        repository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            new EntityNotFoundException(String.format("Não foi encontrado o cliente para o id '%d'", id));
        }

        repository.deleteById(id);
    }

    private ClienteDTO returnDTO(Cliente source) {
        ClienteDTO target = new ClienteDTO();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    private Cliente returnTO(ClientePostDTO source) {
        Cliente target = new Cliente();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    private Cliente returnTO(ClienteDTO source) {
        Cliente target = new Cliente();

        BeanUtils.copyProperties(source, target);

        return target;
    }

}