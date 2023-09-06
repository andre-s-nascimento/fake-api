package net.snascimento.fakeapi.apiv1;

import java.util.List;

import net.snascimento.fakeapi.business.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.snascimento.fakeapi.apiv1.dto.ProductsDTO;
import net.snascimento.fakeapi.business.services.FakeApiService;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

  private final FakeApiService service;
  private final ProdutoService produtoService;

  @Operation(summary = "Busca produtos da API e Salva", method = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Busca Realizada com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao Realizar Busca dos Dados.")
      })
  @PostMapping("/api")
  public ResponseEntity<List<ProductsDTO>> salvaProdutosApi() {
    return ResponseEntity.ok(service.buscaProdutos());
  }

  @Operation(summary = "Salva novos produtos", method = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produto salvo com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos.")
      })
  @PostMapping("/")
  public ResponseEntity<ProductsDTO> salvaProdutos(@RequestBody ProductsDTO productsDTO) {

    return ResponseEntity.ok(produtoService.salvaProdutoDTO(productsDTO));
  }

  @Operation(summary = "Atualiza produto", method = "PUT")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao atualizar o produto.")
      })
  @PutMapping("/")
  public ResponseEntity<ProductsDTO> atualizaProduto(
      @RequestParam("id") String id, @RequestBody ProductsDTO productsDTO) {

    return ResponseEntity.ok(produtoService.updateProduto(id, productsDTO));
  }

  @Operation(summary = "Exclui produto pelo nome", method = "DELETE")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "202", description = "Produto excluído com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao excluir o produto.")
      })
  @DeleteMapping("/")
  public ResponseEntity<Void> deletaProduto(@RequestParam("nome") String nome) {
    produtoService.deletaProduto(nome);
    return ResponseEntity.accepted().build();
  }

  @Operation(summary = "Busca todos os produtos", method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao listar todos os produtos.")
      })
  @GetMapping("/")
  public ResponseEntity<List<ProductsDTO>> buscaTodosProdutos() {

    return ResponseEntity.ok(produtoService.buscaTodosProdutos());
  }

  @Operation(summary = "Busca produto por nome", method = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com Sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro ao buscar o produto.")
      })
  @GetMapping("/{nome}")
  public ResponseEntity<ProductsDTO> buscaProdutoPorNome(@PathVariable("nome") String nome) {

    return ResponseEntity.ok(produtoService.buscaProdutoPorNome(nome));
  }
}
