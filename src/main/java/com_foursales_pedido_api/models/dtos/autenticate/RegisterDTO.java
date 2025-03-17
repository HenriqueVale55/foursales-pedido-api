package com_foursales_pedido_api.models.dtos.autenticate;

import com_foursales_pedido_api.models.enums.UsuarioRole;

public record RegisterDTO(String login, String password, UsuarioRole role) {
}
