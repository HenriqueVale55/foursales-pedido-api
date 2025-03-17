package com_foursales_pedido_api.models.entities;

import com_foursales_pedido_api.models.dtos.pedido.PedidoDto;
import com_foursales_pedido_api.models.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idPedido;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "status")
    private String status;

    @Column(name = "data_pedido")
    private Date dataPedido;

    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public Pedido(PedidoDto pedidoDto, Produto produto, UUID uuid, StatusPedido status, Usuario usuario) {
        this.idPedido = uuid.toString();
        this.produto = produto;
        this.status = status.toString();
        this.dataPedido = new Date();
        this.quantidadeProduto = pedidoDto.getProdutos().stream()
                .filter(produtoDto -> produtoDto.getId().equals(UUID.fromString(produto.getId())))
                .findFirst().get().getQuantidade();
        this.usuario = usuario;
    }
}
