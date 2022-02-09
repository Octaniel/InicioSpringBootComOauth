package com.bsoftware.walkingmoney.resource;

import com.bsoftware.walkingmoney.model.Usuario;
import com.bsoftware.walkingmoney.repository.UsuarioRepository;
import com.bsoftware.walkingmoney.repository.UsuarioRepositoryCustom;
import com.bsoftware.walkingmoney.repository.projection.UsuarioResumo;
import com.bsoftware.walkingmoney.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioResource {

    private final UsuarioRepository UsuarioRepository;

    private final UsuarioService UsuarioService;

    private final UsuarioRepositoryCustom usuarioRepositoryCustom;


    public UsuarioResource(UsuarioRepository UsuarioRepository, UsuarioService UsuarioService, UsuarioRepositoryCustom usuarioRepositoryCustom) {
        this.UsuarioRepository = UsuarioRepository;
        this.UsuarioService = UsuarioService;
        this.usuarioRepositoryCustom = usuarioRepositoryCustom;
    }

    @GetMapping("listar")
    public List<Usuario> listar() {
        return UsuarioRepository.findAll();
    }

    @GetMapping("teste")
    public Page<UsuarioResumo> teste(String nome, Pageable pageable) {
       return usuarioRepositoryCustom.resumo(nome, pageable);
    }

    @GetMapping("/{id}")
    public Usuario atualizar(@PathVariable Integer id) {
        return UsuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario Usuario, HttpServletResponse httpServletResponse) {
        return UsuarioService.salvar(Usuario, httpServletResponse);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Integer id, @Valid @RequestBody Usuario Usuario) {
        return UsuarioService.atualizar(id, Usuario);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        UsuarioRepository.deleteById(id);
    }
}
