package com_foursales_pedido_api.models.dtos.produto;

import com_foursales_pedido_api.models.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDto {

    private String id;

    private String nome;

    private String descricao;

    private Double preco;

    private Integer quantidadeEstoque;

    private Date dataCriacao;

    private String categoria;

    public ProdutoResponseDto(Produto produto) {
        this.id = produto.getId();
        this.setNome(produto.getNome());
        this.setDescricao(produto.getDescricao());
        this.setPreco(produto.getPreco());
        this.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        this.setDataCriacao(produto.getDataCriacao());
        this.setCategoria(produto.getCategoria());
    }
}