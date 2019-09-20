package br.com.cliente.cadastro.modelo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cliente.cadastro.modelo.entity.Cliente;

/**
 * ClienteRepository
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNomeIgnoreCase(String nome);

    Optional<Cliente> findById(Long id);
}