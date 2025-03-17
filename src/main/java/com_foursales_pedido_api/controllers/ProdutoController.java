package com_foursales_pedido_api.controllers;

import com_foursales_pedido_api.models.dtos.produto.ProdutoRequestDto;
import com_foursales_pedido_api.models.dtos.produto.ProdutoResponseDto;
import com_foursales_pedido_api.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        return produtoService.findAll();
    }

    @Operation(summary = "Listar um produto pelo Id", description = "Retorna um produto caso haja um id cadastrado")
    @ApiResponse(responseCode = "200", description = "Retorna um produto caso haja um id cadastrado")
    @GetMapping("/idProduto")
    public ResponseEntity<ProdutoResponseDto> buscarProduto(UUID idProduto){
        return produtoService.findById(idProduto.toString());
    }

    @Operation(summary = "Criar um produto", description = "Cria um produto")
    @ApiResponse(responseCode = "200", description = "Cria um produto")
    @PostMapping()
    public ResponseEntity criarProduto(@Valid @RequestBody ProdutoRequestDto produto){
        return produtoService.save(produto);
    }

    @Operation(summary = "Atualiza um produto", description = "Atualiza um produto")
    @ApiResponse(responseCode = "200", description = "Atualiza um produto")
    @PutMapping("/idProduto")
    public ResponseEntity atualizarProduto(@RequestBody ProdutoRequestDto produto, UUID idProduto){
        return produtoService.update(produto, idProduto.toString());
    }

    @Operation(summary = "Deleta um produto", description = "Deleta um produto")
    @ApiResponse(responseCode = "200", description = "Deleta um produto")
    @DeleteMapping("/idProduto")
    public ResponseEntity deletarProduto(UUID idProduto){
        return produtoService.delete(idProduto);
    }
}
