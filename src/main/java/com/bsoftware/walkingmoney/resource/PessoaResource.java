package com.bsoftware.walkingmoney.resource;


import com.bsoftware.walkingmoney.model.Pessoa;
import com.bsoftware.walkingmoney.repository.PessoaRepository;
import com.bsoftware.walkingmoney.repository.PessoaRepositoryCustom;
import com.bsoftware.walkingmoney.repository.filter.PessoaFilter;
import com.bsoftware.walkingmoney.repository.projection.PessoaResumo;
import com.bsoftware.walkingmoney.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaResource {
    private final PessoaRepository pessoaRepository;

    private final PessoaService pessoaService;

    private final PessoaRepositoryCustom pessoaRepositoryCustom;

    public PessoaResource(PessoaRepository pessoaRepository, PessoaService pessoaService, PessoaRepositoryCustom pessoaRepositoryCustom) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
        this.pessoaRepositoryCustom = pessoaRepositoryCustom;
    }

    @GetMapping("listar")
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @GetMapping
    public Page<PessoaResumo> listarTabela(PessoaFilter pessoaFilter, Pageable pageable) {
        return pessoaRepositoryCustom.resumo(pessoaFilter, pageable);
    }

    @GetMapping("/{id}")
    public Pessoa atualizar(@PathVariable Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse httpServletResponse) {
        return pessoaService.salvar(pessoa, httpServletResponse);
    }

    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Integer id, @RequestBody String idOneSignal) {
        return pessoaService.atualizar(id, idOneSignal);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/{id}")
    public void remover(@PathVariable Integer id) {
        pessoaRepository.deleteById(id);
    }

}
