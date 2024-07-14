package com.kharitonov.bankAppTest.repository.impl;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CreditRepository {
    private static final String GET_ALL = "SELECT c FROM Credit c";
    @PersistenceContext
    private EntityManager em;
    public Credit getById(Long id) {
        return em.find(Credit.class, id);
    }
    @Transactional
    public void deleteById(Credit credit) {
        em.remove(credit);
    }

    @Transactional
    public void createById(Credit credit) {
        em.persist(credit);
    }
    @Transactional
    public void updateById(Credit credit) {
        em.merge(credit);

    }
    public List<Credit> getAllCredits() {
        return em.createQuery(GET_ALL, Credit.class).getResultList();
    }


}
