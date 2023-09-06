package net.snascimento.fakeapi.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.snascimento.fakeapi.infrastructure.entities.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String>{

    Boolean existsByNome(String nome);
    ProdutoEntity findByNome(String nome);

    void deleteByNome(String nome);
}
