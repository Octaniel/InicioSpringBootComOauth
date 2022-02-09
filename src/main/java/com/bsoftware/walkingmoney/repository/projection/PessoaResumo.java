package com.bsoftware.walkingmoney.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResumo {
    private Integer id;
    private String nome;
    private String email;
    private String nomeBanco;
    private String nConta;
    private String morada;

    public PessoaResumo(Integer id, String nome, String email, String nomeBanco, String nConta, String morada) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nomeBanco = nomeBanco;
        this.nConta = nConta;
        this.morada = morada;
    }
}
