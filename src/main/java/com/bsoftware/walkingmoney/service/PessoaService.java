package com.bsoftware.walkingmoney.service;

import com.bsoftware.walkingmoney.event.RecursoCriadoEvent;
import com.bsoftware.walkingmoney.model.Pessoa;
import com.bsoftware.walkingmoney.repository.PessoaRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PessoaService {
    private final ApplicationEventPublisher publisher;

    private final PessoaRepository pessoaRepository;

    public PessoaService(ApplicationEventPublisher publisher, PessoaRepository pessoaRepository) {
        this.publisher = publisher;
        this.pessoaRepository = pessoaRepository;
    }


    public ResponseEntity<Pessoa> salvar(Pessoa pessoa, HttpServletResponse httpServletResponse) {
        LocalDateTime localDateTime = LocalDateTime.now();
        pessoa.setDataAlteracao(localDateTime);
        pessoa.setDataCriacao(localDateTime);

        List<Pessoa> all = pessoaRepository.findAll();
        while (true) {
            String s = criarSenha();
            AtomicReference<Boolean> b = new AtomicReference<>(false);
            all.forEach(x -> {
                if (s.equals(x.getCodigo())) b.set(true);
            });
            if (b.get()) continue;
            pessoa.setCodigo(s);
            break;
        }

        Pessoa save = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, httpServletResponse, save.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    private String criarSenha() {
        RandomString random = new RandomString();
        Random random1 = new Random();
        String s = random.nextString();
        String senha = (random1.nextInt(9) + 1) + "" + s.charAt(1) + "" + (random1.nextInt(9) + 1) + "" + s.charAt(3);
        senha += (random1.nextInt(9) + 1) + "" + s.charAt(1) + "" + (random1.nextInt(9) + 1) + "" + s.charAt(3);
        return senha;
    }

    public Pessoa atualizar(Integer id, String idOneSignal) {
        Optional<Pessoa> byId = pessoaRepository.findById(id);
        byId.get().setDataAlteracao(LocalDateTime.now());
        byId.get().setIdOneSignal(idOneSignal);
        return pessoaRepository.save(byId.get());
    }
}
