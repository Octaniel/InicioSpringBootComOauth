package com.bsoftware.walkingmoney.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @NotBlank(message = "O nome do usuario é obrigatorio")
    @Column(name = "nome_usuario")
    private String nome;

    @Column(name = "senha_usuario")
    private String senha;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Pessoa pessoa;


    @Transient
    private String confirmacaoSenha;

    @Size(min = 1, message = "Pelo menos um grupo deve ser selecionado para o usuario")
    @NotNull(message = "Pelo menos um grupo deve ser selecionado para o usuario")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id")
            , inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos;

    @Column(name = "dt_cria")
    private LocalDateTime dataCriacao;

    @Column(name = "dt_alter")
    private LocalDateTime dataAlteracao;
}
