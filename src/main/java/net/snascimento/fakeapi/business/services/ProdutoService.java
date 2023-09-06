package net.snascimento.fakeapi.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.snascimento.fakeapi.infrastructure.entities.ProdutoEntity;
import net.snascimento.fakeapi.infrastructure.repositories.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoEntity salvaProdutos(ProdutoEntity entity){
        try{
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos" + e);
        }
    }

    public List<ProdutoEntity> buscaTodosProdutos(){
        try{
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os produtos" + e);
        }
    }
    
}
