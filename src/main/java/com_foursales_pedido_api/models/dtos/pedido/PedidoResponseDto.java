package com_foursales_pedido_api.models.dtos.pedido;

import com_foursales_pedido_api.models.dtos.produto.ProdutoResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoResponseDto {

    private String id;
    private Date dataPedido;
    private double valorTotal;
    private String status;
    private double quantidadePedido;
    private List<ProdutoResponseDto> produtos;

    public PedidoResponseDto(String id, Date dataPedido, double valorTotal, List<ProdutoResponseDto> produtos, String status, double quantidadePedido) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.produtos = produtos;
        this.status = status;
        this.quantidadePedido = quantidadePedido;
    }
}
