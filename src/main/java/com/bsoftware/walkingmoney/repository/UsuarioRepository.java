package com.bsoftware.walkingmoney.repository;

import com.bsoftware.walkingmoney.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByClientePessoaEmail(String email);

    Optional<Usuario> findByEmpresaPessoaEmail(String email);
}
