package br.com.cliente.cadastro.modelo.repositories;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cliente.cadastro.modelo.entity.Cliente;

/**
 * ClienteRepository
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(@NotEmpty String nome);

    Optional<Cliente> findById(@NotNull Long id);
}