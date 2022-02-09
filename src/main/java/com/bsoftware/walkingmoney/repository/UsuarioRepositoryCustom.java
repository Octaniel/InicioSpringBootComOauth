package com.bsoftware.walkingmoney.repository;

import com.bsoftware.walkingmoney.model.*;
import com.bsoftware.walkingmoney.repository.projection.UsuarioResumo;
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
public class UsuarioRepositoryCustom {
    @PersistenceContext
    private EntityManager Manager;

    public Page<UsuarioResumo> resumo(String nome, Pageable pageable) {
        CriteriaBuilder builder = Manager.getCriteriaBuilder();
        CriteriaQuery<UsuarioResumo> query = builder.createQuery(UsuarioResumo.class);
        Root<Usuario> rootcli = query.from(Usuario.class);

        query.select(builder.construct(UsuarioResumo.class
                , rootcli.get(Usuario_.nome),
                rootcli.get(Usuario_.pessoa).get(Pessoa_.id)));
        query.groupBy(rootcli.get(Usuario_.nome),
                rootcli.get(Usuario_.pessoa).get(Pessoa_.id));

        Predicate[] predicates = criarPredicates(nome, builder, rootcli);
        query.where(predicates);

        TypedQuery<UsuarioResumo> typedQuery = Manager.createQuery(query);
        int size = typedQuery.getResultList().size();
        List<UsuarioResumo> usuarioResumos = typedQuery.getResultList();
        List<UsuarioResumo> usuarioResumos1 = new ArrayList<>();
        for(int i=0;i<size;i++){
            UsuarioResumo usuarioResumo = usuarioResumos.get(i);
            usuarioResumos1.add(usuarioResumo);
        }


        adicionarRestricoesDePagina(typedQuery, pageable);
        return new PageImpl<>(usuarioResumos1, pageable, size);
    }

    private void adicionarRestricoesDePagina(TypedQuery<UsuarioResumo> query, Pageable pageable) {
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    }

    private Predicate[] criarPredicates(String nome, CriteriaBuilder builder, Root<Usuario> root) {
        List<Predicate> predicates = new ArrayList<>();


        if (!StringUtils.isEmpty(nome)) {
            predicates.add(builder.like(builder.lower(root.get(Usuario_.NOME)), "%" + nome.toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }

}
