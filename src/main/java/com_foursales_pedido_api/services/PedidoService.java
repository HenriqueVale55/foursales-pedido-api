package com_foursales_pedido_api.services;

import com_foursales_pedido_api.exceptions.EstoqueInsuficienteException;
import com_foursales_pedido_api.models.dtos.pedido.PedidoDto;
import com_foursales_pedido_api.models.dtos.pedido.PedidoResponseDto;
import com_foursales_pedido_api.models.dtos.produto.ProdutoResponseDto;
import com_foursales_pedido_api.models.dtos.usuarios.UsuarioCompraDTO;
import com_foursales_pedido_api.models.dtos.usuarios.UsuarioTicketMedioDTO;
import com_foursales_pedido_api.models.entities.Pedido;
import com_foursales_pedido_api.models.entities.Produto;
import com_foursales_pedido_api.models.entities.Usuario;
import com_foursales_pedido_api.models.enums.StatusPedido;
import com_foursales_pedido_api.repositories.PedidoRepository;
import com_foursales_pedido_api.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PedidoService {

    PedidoRepository pedidoRepository;
    ProdutoService produtoService;
    UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Void> createPedido(PedidoDto pedidoDto) {
        try{
            UUID uuid = UUID.randomUUID();

            Usuario usuario = ObterUser();

            pedidoDto.getProdutos().forEach(p -> {
                ProdutoResponseDto produto = produtoService.findById(p.getId().toString()).getBody();
                if (produto == null)
                    throw new NotFoundException("Produto no encontrado");

                if (produto.getQuantidadeEstoque() < p.getQuantidade()){
                    pedidoRepository.save((new Pedido(pedidoDto, new Produto(produto), uuid, StatusPedido.CANCELADO_AUTOMATICAMENTE,
                            usuario)));
                    throw new EstoqueInsuficienteException("Quantidade de estoque para o produto " +
                            produto.getNome() + " insuficiente");
                }
                else{
                    pedidoRepository.save((new Pedido(pedidoDto, new Produto(produto), uuid, StatusPedido.PENDENTE, usuario)));

                }
            });
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ResponseEntity<Void> pagarPedido(UUID idPedido) {
        Usuario usuario = ObterUser();
        Optional<List<Pedido>> pedidos = pedidoRepository.findByIdAndUserIdList(idPedido.toString(), usuario.getId());

        if (pedidos.isPresent()) {
            pedidos.get().forEach(p -> {
                p.setStatus(StatusPedido.PAGO.toString());

                p.getProduto().setQuantidadeEstoque(p.getProduto().getQuantidadeEstoque() - p.getQuantidadeProduto());

                pedidoRepository.save(p);
            });
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<PedidoResponseDto> buscarPedidoPorId(String idPedido) {
        Usuario usuario = ObterUser();
        Optional<List<Pedido>> pedidos = pedidoRepository.findByIdAndUserIdList(idPedido, usuario.getId());
        if (pedidos.isPresent()) {
            double valorTotal = pedidos.get().stream()
                    .mapToDouble(p -> p.getProduto().getPreco())
                    .sum();
            Pedido pedido = pedidos.get().get(0);

            List<ProdutoResponseDto> produtos = pedidos.get().stream()
                    .map(Pedido::getProduto)
                    .map(ProdutoResponseDto::new)
                    .toList();

            return new ResponseEntity<>(new PedidoResponseDto(pedido.getIdPedido(), pedido.getDataPedido(), valorTotal,
                    produtos, pedido.getStatus()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new PedidoResponseDto(), HttpStatus.NOT_FOUND);
    }

    private Usuario ObterUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication != null && authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return usuarioRepository.findByLoginEntity(username);
    }

    public ResponseEntity<List<PedidoResponseDto>> buscarPedidos() {
        Usuario usuario = ObterUser();
        Optional<List<Pedido>> pedidosOptional = pedidoRepository.findByUserIdList(usuario.getId());

        if (pedidosOptional.isPresent() && !pedidosOptional.get().isEmpty()) {
            List<Pedido> pedidos = pedidosOptional.get();

            List<PedidoResponseDto> pedidosResponse = pedidos.stream()
                    .collect(Collectors.groupingBy(Pedido::getIdPedido))
                    .entrySet().stream()
                    .map(entry -> {
                        String idPedido = entry.getKey();
                        List<Pedido> itensDoPedido = entry.getValue();

                        double valorTotal = itensDoPedido.stream()
                                .mapToDouble(p -> p.getProduto().getPreco() * p.getQuantidadeProduto())
                                .sum();

                        Date dataPedido = itensDoPedido.get(0).getDataPedido();
                        String status = itensDoPedido.get(0).getStatus();

                        List<ProdutoResponseDto> produtos = itensDoPedido.stream()
                                .map(Pedido::getProduto)
                                .map(ProdutoResponseDto::new)
                                .toList();

                        return new PedidoResponseDto(idPedido, dataPedido, valorTotal, produtos, status);
                    })
                    .toList();

            return new ResponseEntity<>(pedidosResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<List<UsuarioCompraDTO>> buscarTop5Usuarios() {
        List<Pedido> pedidosPagos = pedidoRepository.findAllByStatus("PAGO");

        Map<String, UsuarioCompraDTO> mapaUsuarios = new HashMap<>();

        for (Pedido pedido : pedidosPagos) {
            Usuario usuario = pedido.getUsuario();
            Produto produto = pedido.getProduto();

            UsuarioCompraDTO dto = mapaUsuarios.computeIfAbsent(usuario.getId(), id ->
                    new UsuarioCompraDTO(
                            usuario.getId(),
                            usuario.getLogin(),
                            0,
                            0,
                            0d
                    )
            );

            dto.setTotalPedidos(dto.getTotalPedidos() + 1);
            dto.setTotalQuantidadeComprada(dto.getTotalQuantidadeComprada() + pedido.getQuantidadeProduto());
            Double valorGasto = Double.valueOf(pedido.getQuantidadeProduto()) * (produto.getPreco());
            dto.setTotalGasto(dto.getTotalGasto() + valorGasto);
        }

        List<UsuarioCompraDTO> topUsuarios = mapaUsuarios.values().stream()
                .sorted(Comparator.comparing(UsuarioCompraDTO::getTotalGasto).reversed())
                .limit(5)
                .toList();

        return ResponseEntity.ok(topUsuarios);
    }

    public ResponseEntity<String> buscarValorFaturadoNoMes(int mes, int ano) {
        return new ResponseEntity<>("R$ " + pedidoRepository.findValorFaturadoByMes(mes,ano), HttpStatus.OK);
    }
    public ResponseEntity<List<UsuarioTicketMedioDTO>> buscarTicketMedioPorUsuario(){
        List<Pedido> pedidosPagos = pedidoRepository.findAllByStatus("PAGO");

        Map<String, UsuarioTicketMedioDTO> mapaUsuarios = new HashMap<>();

        for (Pedido pedido : pedidosPagos) {
            Usuario usuario = pedido.getUsuario();
            Produto produto = pedido.getProduto();

            UsuarioTicketMedioDTO dto = mapaUsuarios.computeIfAbsent(usuario.getLogin(), login ->
                    new UsuarioTicketMedioDTO(
                            usuario.getLogin(),
                            0.0
                    )
            );

            Double valorGasto = Double.valueOf(pedido.getQuantidadeProduto()) * (produto.getPreco());

            double novoTicketMedio = (dto.getTicketMedio() * dto.getNumeroPedidos() + valorGasto.doubleValue()) / (dto.getNumeroPedidos() + 1);
            dto.setTicketMedio(novoTicketMedio);

            dto.setNumeroPedidos(dto.getNumeroPedidos() + 1);
        }

        return ResponseEntity.ok(new ArrayList<>(mapaUsuarios.values()));
    }
}
