package com.bsoftware.walkingmoney.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFilter {
    private String nome;
    private String email;
    private String nomeBanco;
}
