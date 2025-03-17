package com_foursales_pedido_api.models.dtos.pedido;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoPedidoRequestDto {

    private UUID id;

    @Positive(message = "O valor deve ser maior que 0")
    private int quantidade;
}
