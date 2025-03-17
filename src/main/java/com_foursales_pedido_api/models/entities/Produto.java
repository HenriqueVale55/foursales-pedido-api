package com_foursales_pedido_api.models.entities;

import com_foursales_pedido_api.models.dtos.produto.ProdutoRequestDto;
import com_foursales_pedido_api.models.dtos.produto.ProdutoResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private double preco;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "categoria")
    private String categoria;


    public Produto(ProdutoRequestDto produtoRequestDto, String idProduto) {
        this.id = idProduto;
        this.nome = produtoRequestDto.getNome();
        this.descricao = produtoRequestDto.getDescricao();
        this.preco = produtoRequestDto.getPreco();
        this.dataCriacao = produtoRequestDto.getDataCriacao();
        this.quantidadeEstoque = produtoRequestDto.getQuantidadeEstoque();
        this.categoria = produtoRequestDto.getCategoria();
    }

    public Produto(ProdutoResponseDto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.dataCriacao = produto.getDataCriacao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.categoria = produto.getCategoria();
    }

    public Produto(ProdutoRequestDto produtoRequestDto) {
        this.nome = produtoRequestDto.getNome();
        this.descricao = produtoRequestDto.getDescricao();
        this.preco = produtoRequestDto.getPreco();
        this.dataCriacao = produtoRequestDto.getDataCriacao();
        this.quantidadeEstoque = produtoRequestDto.getQuantidadeEstoque();
        this.categoria = produtoRequestDto.getCategoria();
    }
}
