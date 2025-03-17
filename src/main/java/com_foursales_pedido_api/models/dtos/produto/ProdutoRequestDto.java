package com_foursales_pedido_api.models.dtos.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoRequestDto {

    private String nome;

    private String descricao;

    private Double preco;

    private Integer quantidadeEstoque;

    private Date dataCriacao;

    private String categoria;
}
