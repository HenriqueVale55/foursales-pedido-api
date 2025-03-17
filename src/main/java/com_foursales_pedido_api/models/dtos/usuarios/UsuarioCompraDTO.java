package com_foursales_pedido_api.models.dtos.usuarios;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioCompraDTO {
    private String usuarioId;
    private String usuarioLogin;
    private Integer totalPedidos;
    private Integer totalQuantidadeComprada;
    private Double totalGasto;

    public UsuarioCompraDTO(String usuarioId, String usuarioLogin, Integer totalPedidos, Integer totalQuantidadeComprada, Double totalGasto) {
        this.usuarioId = usuarioId;
        this.usuarioLogin = usuarioLogin;
        this.totalPedidos = totalPedidos;
        this.totalQuantidadeComprada = totalQuantidadeComprada;
        this.totalGasto = totalGasto;
    }
}
