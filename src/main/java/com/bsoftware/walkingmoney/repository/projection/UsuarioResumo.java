package com.bsoftware.walkingmoney.repository.projection;

import com.bsoftware.walkingmoney.model.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioResumo {
    private String nome;
    private Integer idPessoa;
    private Long quantidade;
    private List<Pessoa> contactos;

    public UsuarioResumo(String nome, Integer idPessoa, Long quantidade) {
        this.nome = nome;
        this.idPessoa = idPessoa;
        this.quantidade = quantidade;
        this.contactos = new ArrayList<>();
    }
}
