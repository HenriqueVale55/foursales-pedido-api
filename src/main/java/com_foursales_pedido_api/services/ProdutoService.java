package com_foursales_pedido_api.services;

import com_foursales_pedido_api.models.dtos.produto.ProdutoRequestDto;
import com_foursales_pedido_api.models.dtos.produto.ProdutoResponseDto;
import com_foursales_pedido_api.models.entities.Produto;
import com_foursales_pedido_api.repositories.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ResponseEntity<List<ProdutoResponseDto>> findAll() {
        return new ResponseEntity<>(produtoRepository.findAll().stream()
                        .map(ProdutoResponseDto::new)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }
    public ResponseEntity<ProdutoResponseDto> findById(String idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto.toString());
        if(produto.isPresent()){
            return new ResponseEntity<>(new ProdutoResponseDto(produto.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProdutoResponseDto(), HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Void> save(ProdutoRequestDto produtoRequestDto){
        Produto produto = new Produto(produtoRequestDto);
        produtoRepository.save(produto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> update(ProdutoRequestDto produtoRequestDto, String idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto.toString());
        if(produto.isPresent()){
            produtoRepository.save(new Produto(produtoRequestDto, idProduto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Void> delete(UUID idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto.toString());
        if(produto.isPresent()){
            produtoRepository.delete(produto.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
