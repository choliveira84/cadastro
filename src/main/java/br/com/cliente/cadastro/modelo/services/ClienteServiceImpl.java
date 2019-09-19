package br.com.cliente.cadastro.modelo.services;

import br.com.cliente.cadastro.exceptions.InvalidDateException;
import br.com.cliente.cadastro.exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cliente.cadastro.controllers.cliente.ClienteDTO;
import br.com.cliente.cadastro.controllers.cliente.ClientePatchDTO;
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

    @Transactional
    @Override
    public ClienteDTO save(ClientePostDTO dto) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNascimento = dto.getDataNascimento();

        if (dataNascimento.isAfter(dataAtual)) {
            throw new InvalidDateException("A data atual não pode ser menor que a data de nascimento");
        }

        Integer idade = Period.between(dataNascimento, dataAtual).getYears();

        return returnDTO(repository.save(returnTO(dto, idade)));
    }

    @Transactional
    @Override
    public ClienteDTO update(ClientePatchDTO dto, Long id) {
        Cliente cliente = returnTO(findById(id), dto.getNome());

        return returnDTO(repository.save(cliente));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Não foi encontrado o cliente para o id '%d'", id));
        }

        repository.deleteById(id);
    }

    private ClienteDTO returnDTO(Cliente source) {
        ClienteDTO target = new ClienteDTO();

        BeanUtils.copyProperties(source, target);

        return target;
    }

    private Cliente returnTO(ClientePostDTO source, Integer idade) {
        Cliente target = new Cliente();

        BeanUtils.copyProperties(source, target);

        target.setIdade(idade);

        return target;
    }

    private Cliente returnTO(ClienteDTO source, String nome) {
        Cliente target = new Cliente();

        BeanUtils.copyProperties(source, target);

        target.setNome(nome);

        return target;
    }

}