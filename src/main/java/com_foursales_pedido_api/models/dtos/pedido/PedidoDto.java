package com_foursales_pedido_api.models.dtos.pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDto {

    @JsonIgnore
    private String id;

    private Date dataPedido;

    private List<ProdutoPedidoRequestDto> produtos;
}
