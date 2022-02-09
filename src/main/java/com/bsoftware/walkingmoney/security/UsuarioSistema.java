package com.bsoftware.walkingmoney.security;

import com.bsoftware.walkingmoney.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {

    private Usuario usuario;

    public UsuarioSistema(Usuario usu, Collection<? extends GrantedAuthority> authorities, String email) {
        super(email, usu.getSenha(), authorities);
        usuario = usu;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
