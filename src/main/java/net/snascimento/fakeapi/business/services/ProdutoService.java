package net.snascimento.fakeapi.business.services;

import java.util.List;

import net.snascimento.fakeapi.apiv1.dto.ProductsDTO;
import net.snascimento.fakeapi.business.converter.ProdutoConverter;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.snascimento.fakeapi.infrastructure.entities.ProdutoEntity;
import net.snascimento.fakeapi.infrastructure.repositories.ProdutoRepository;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProdutoService {

  private final ProdutoRepository repository;
  private final ProdutoConverter converter;

  public ProdutoEntity salvaProdutos(ProdutoEntity entity) {
    try {
      return repository.save(entity);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao salvar Produtos" + e);
    }
  }

  public ProductsDTO salvaProdutoDTO(ProductsDTO productsDTO) {
    try {
      ProdutoEntity entity = converter.toEntity(productsDTO);
      return converter.toDTO(repository.save(entity));
    } catch (Exception e) {
      throw new RuntimeException(format("Erro ao salvar produto: %s", productsDTO.getNome()), e);
    }
  }

  public List<ProductsDTO> buscaTodosProdutos() {
    try {
      return converter.toListDTO(repository.findAll());
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar todos os produtos" + e);
    }
  }

  public ProductsDTO buscaProdutoPorNome(String nome) {
    try {
      return converter.toDTO(repository.findByNome(nome));
    } catch (Exception e) {
      throw new RuntimeException(format("Erro ao buscar produto por nome: %s", nome), e);
    }
  }

  public void deletaProduto(String nome) {
    try {
      repository.deleteByNome(nome);
    } catch (Exception e) {
      throw new RuntimeException(format("Erro ao deletar produto por nome: %s", nome), e);
    }
  }

  public Boolean existsPorNome(String nome) {
    try {
      return repository.existsByNome(nome);
    } catch (Exception e) {
      throw new RuntimeException(format("Erro ao buscar produto por nome: %s", nome), e);
    }
  }

  public ProductsDTO updateProduto(String id, ProductsDTO productsDTO) {
    try {
      ProdutoEntity entity =
          repository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Id não existe no banco de dados."));
      salvaProdutos(converter.toEntityUpdate(entity, productsDTO, id));
      return converter.toDTO(repository.findByNome(entity.getNome()));
    } catch (Exception e) {
      throw new RuntimeException(format("Erro ao atualizar produto: %s", productsDTO.getNome()), e);
    }
  }
}
