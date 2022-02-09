package com.bsoftware.walkingmoney.repository;

import com.bsoftware.walkingmoney.model.Pessoa;
import com.bsoftware.walkingmoney.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByCodigo(String codigo);
}
