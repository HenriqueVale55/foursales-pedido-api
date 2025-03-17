package com_foursales_pedido_api.controllers;

import com_foursales_pedido_api.models.dtos.pedido.PedidoDto;
import com_foursales_pedido_api.models.dtos.pedido.PedidoResponseDto;
import com_foursales_pedido_api.models.dtos.usuarios.UsuarioCompraDTO;
import com_foursales_pedido_api.models.dtos.usuarios.UsuarioTicketMedioDTO;
import com_foursales_pedido_api.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Criar um pedido", description = "Cria um pedido")
    @ApiResponse(responseCode = "200", description = "Cria um pedido")
    @PostMapping()
    public ResponseEntity<Void> postPedido(@Valid @RequestBody PedidoDto pedidoDto){
        return pedidoService.createPedido(pedidoDto);
    }

    @Operation(summary = "Realizar pagamento do pedido", description = "Realizar pagamento do pedido")
    @ApiResponse(responseCode = "200", description = "Realizar pagamento do pedido")
    @PostMapping("/idPedido")
    public ResponseEntity<Void> postPagarPedido(UUID idPedido){
        return pedidoService.pagarPedido(idPedido);
    }

    @Operation(summary = "Buscar pedido por id", description = "Buscar pedido por id")
    @ApiResponse(responseCode = "200", description = "Buscar pedido por id")
    @GetMapping("/idPedido")
    public ResponseEntity<PedidoResponseDto> getPedidoPorId(UUID idPedido){
        return pedidoService.buscarPedidoPorId(idPedido.toString());
    }

    @Operation(summary = "Buscar pedidos do usuário informado", description = "Buscar pedidos do usuário informado")
    @ApiResponse(responseCode = "200", description = "Buscar pedidos do usuário informado")
    @GetMapping("")
    public ResponseEntity<List<PedidoResponseDto>> getPedidosDoUsuario(){
        return pedidoService.buscarPedidos();
    }

    @Operation(summary = "Buscar os 5 usuários que mais compraram", description = "Buscar os 5 usuários que mais compraram")
    @ApiResponse(responseCode = "200", description = "Buscar os 5 usuários que mais compraram")
    @GetMapping("/usuarios-mais-compraram")
    public ResponseEntity<List<UsuarioCompraDTO>> getUsuariosQueMaisCompraram(){
        return pedidoService.buscarTop5Usuarios();
    }

    @Operation(summary = "Buscar valor faturado no mês informado", description = "Buscar valor faturado no mês informado")
    @ApiResponse(responseCode = "200", description = "Buscar valor faturado no mês informado")
    @GetMapping("/mes/ano")
    public ResponseEntity<String> getValorFaturadoNoMes(int mes, int ano){
        return pedidoService.buscarValorFaturadoNoMes(mes,ano);
    }

    @Operation(summary = "Buscar ticket médio dos usuários", description = "Buscar ticket médio dos usuários")
    @ApiResponse(responseCode = "200", description = "Buscar ticket médio dos usuários")
    @GetMapping("/ticket-medio-por-usuario")
    public ResponseEntity<List<UsuarioTicketMedioDTO>> getTicketMedioPorUsuario() {
        return pedidoService.buscarTicketMedioPorUsuario();
    }


}
