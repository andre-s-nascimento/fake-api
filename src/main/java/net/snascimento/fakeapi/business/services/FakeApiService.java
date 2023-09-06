package net.snascimento.fakeapi.business.services;

import static java.lang.String.format;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.snascimento.fakeapi.apiv1.dto.ProductsDTO;
import net.snascimento.fakeapi.business.converter.ProdutoConverter;
import net.snascimento.fakeapi.infrastructure.client.FakeApiClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FakeApiService {
  private final FakeApiClient cliente;
  private final ProdutoConverter converter;
  private final ProdutoService produtoService;

  public List<ProductsDTO> buscaProdutos() {

    try {

      List<ProductsDTO> produtosDto = cliente.buscaListaProdutos();
      produtosDto.forEach(
          produto -> {
            if (produtoService.existsPorNome(produto.getNome()).equals(false)) {
              produtoService.salvaProdutos(converter.toEntity(produto));
            }
//            throw new RuntimeException(
//                format("Produto já cadastrado no banco de dados", produto.getNome()));
          });
      return produtoService.buscaTodosProdutos();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar e gravar produtos no banco de dados");
    }
  }
}
