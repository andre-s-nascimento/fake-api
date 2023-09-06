package net.snascimento.fakeapi.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.snascimento.fakeapi.apiv1.dto.ProductsDTO;
import net.snascimento.fakeapi.business.converter.ProdutoConverter;
import net.snascimento.fakeapi.infrastructure.client.FakeApiClient;

@Service
@RequiredArgsConstructor
public class FakeApiService {
    private final FakeApiClient cliente;
    private final ProdutoConverter converter;
    private final ProdutoService produtoService;

    public List<ProductsDTO> buscaProdutos(){
        List<ProductsDTO> produtosDto = cliente.buscaListaProdutos();
        produtosDto.forEach(
            produto -> produtoService.salvaProdutos(converter.toEntity(produto))
        );
        return produtosDto;
    }

}
