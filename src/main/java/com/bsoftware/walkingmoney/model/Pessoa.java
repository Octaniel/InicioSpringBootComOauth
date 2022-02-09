package com.bsoftware.walkingmoney.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pessoa")
@Getter
@Setter
@EqualsAndHashCode
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Integer id;

    @NotBlank(message = "O nome é obrigatorio")
    @Column(name = "nome_pessoa")
    private String nome;

    @NotBlank(message = "O email é obrigatorio")
    @Column(name = "email_pessoa")
    private String email;

    @NotNull(message = "Número da conta é obrigatorio")
    @Column(name = "numero_conta")
    private String nConta;

    @NotNull(message = "Morada é obrigatorio")
    @Column(name = "morada_pessoa")
    private String morada;

    @NotNull(message = "Telemovel é obrigatorio")
    @Column(name = "telemovel_pessoa")
    private String telemovel;

    @NotNull(message = "NIF é obrigatorio")
    @Column(name = "nif_pessoa")
    private String nif;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "dt_cria")
    private LocalDateTime dataCriacao;

    @Column(name = "dt_alter")
    private LocalDateTime dataAlteracao;

    @Column(name = "saldo_pessoa")
    private Double saldo;

    @Column(name = "codigo_pessoa")
    private String codigo;

    @Column(name = "id_one_signal")
    private String idOneSignal;
}
