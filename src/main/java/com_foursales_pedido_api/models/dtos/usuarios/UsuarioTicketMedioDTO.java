package com_foursales_pedido_api.models.dtos.usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTicketMedioDTO {
    private String login;
    private double ticketMedio;
    private int numeroPedidos;

    public UsuarioTicketMedioDTO(String login, double ticketMedio) {
        this.login = login;
        this.ticketMedio = ticketMedio;
        this.numeroPedidos = 0;
    }
}
