package br.com.cliente.cadastro.modelo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cliente.cadastro.modelo.entity.Cidade;

/**
 * CidadesRepository
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findByNome(String nome);

	List<Cidade> findByEstado(String siglaEstado);
}