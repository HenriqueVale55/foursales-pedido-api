package com_foursales_pedido_api.repositories;

import com_foursales_pedido_api.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    UserDetails findByLogin(String login);

    @Query(value = "SELECT * FROM usuario WHERE login = ?1",nativeQuery = true)
    Usuario findByLoginEntity(String login);
}
