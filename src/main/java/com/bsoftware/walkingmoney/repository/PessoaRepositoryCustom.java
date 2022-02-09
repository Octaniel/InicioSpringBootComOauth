package com.bsoftware.walkingmoney.repository;

import com.bsoftware.walkingmoney.model.Pessoa;
import com.bsoftware.walkingmoney.model.Pessoa_;
import com.bsoftware.walkingmoney.repository.filter.PessoaFilter;
import com.bsoftware.walkingmoney.repository.projection.PessoaResumo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PessoaRepositoryCustom {
    @PersistenceContext
    private EntityManager Manager;

    public Page<PessoaResumo> resumo(PessoaFilter pessoaFilter, Pageable pageable) {
        CriteriaBuilder builder = Manager.getCriteriaBuilder();
        CriteriaQuery<PessoaResumo> query = builder.createQuery(PessoaResumo.class);
        Root<Pessoa> rootcli = query.from(Pessoa.class);

        query.select(builder.construct(PessoaResumo.class
                , rootcli.get(Pessoa_.id), rootcli.get(Pessoa_.nome), rootcli.get(Pessoa_.email), rootcli.get(Pessoa_.nConta)
                , rootcli.get(Pessoa_.morada)));

        Predicate[] predicates = criarPredicates(pessoaFilter, builder, rootcli);
        query.where(predicates);

        TypedQuery<PessoaResumo> typedQuery = Manager.createQuery(query);
        int size = typedQuery.getResultList().size();
        adicionarRestricoesDePagina(typedQuery, pageable);
        return new PageImpl<>(typedQuery.getResultList(), pageable, size);
    }

    private void adicionarRestricoesDePagina(TypedQuery<PessoaResumo> query, Pageable pageable) {
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    }

    private Predicate[] criarPredicates(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(Pessoa_.NOME)), "%" + pessoaFilter.getNome().toLowerCase() + "%"));
        }
        if (!StringUtils.isEmpty(pessoaFilter.getEmail())) {
            predicates.add(builder.like(root.get(Pessoa_.EMAIL), "%" + pessoaFilter.getEmail() + "%"));
        }
        return predicates.toArray(new Predicate[0]);
    }

}
