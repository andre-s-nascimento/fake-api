package net.snascimento.fakeapi.business.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import net.snascimento.fakeapi.apiv1.dto.ProductsDTO;
import net.snascimento.fakeapi.infrastructure.entities.ProdutoEntity;

@Component
public class ProdutoConverter {

    public ProdutoEntity toEntity(ProductsDTO dto){
        return ProdutoEntity.builder()
        .id(String.valueOf(UUID.randomUUID()))
        .nome(dto.getNome())
        .categoria(dto.getCategoria())
        .descricao(dto.getDescricao())
        .preco(dto.getPreco())
        .imagem(dto.getImagem())
        .dataInclusao(LocalDateTime.now())
        .build();
    }
    
    public ProductsDTO toDTO(ProdutoEntity entity){
        return ProductsDTO.builder()
        .entityId(entity.getId())
        .nome(entity.getNome())
        .categoria(entity.getCategoria())
        .descricao(entity.getDescricao())
        .preco(entity.getPreco())
        .imagem(entity.getImagem())
        .build();
    }

    public List<ProductsDTO> toListDTO(List<ProdutoEntity> entityList){
        return entityList.stream().map(this::toDTO).toList();
    }
}
