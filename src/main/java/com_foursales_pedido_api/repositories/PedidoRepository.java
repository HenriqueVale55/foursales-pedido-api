package com_foursales_pedido_api.repositories;

import com_foursales_pedido_api.models.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query(value = "SELECT * FROM pedido WHERE id_pedido = ?1 AND id_usuario = ?2", nativeQuery = true)
    Optional<List<Pedido>> findByIdAndUserIdList(String id, String usuario);

    @Query(value = "SELECT * FROM pedido WHERE id_usuario = ?1", nativeQuery = true)
    Optional<List<Pedido>> findByUserIdList(String userId);

    List<Pedido> findAllByStatus(String status);

    @Query(value = "SELECT SUM(p.quantidade_produto * pr.preco) " +
            "FROM pedido p " +
            "JOIN produto pr " +
            "WHERE p.status = 'PAGO' " +
            "AND MONTH(p.data_pedido) = :mes " +
            "AND YEAR(p.data_pedido) = :ano", nativeQuery = true)
    Double findValorFaturadoByMes(@Param("mes") int mes, @Param("ano") int ano);
}
