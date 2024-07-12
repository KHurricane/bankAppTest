package com.kharitonov.bankAppTest.repository.impl;


import com.kharitonov.bankAppTest.entity.Bank;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BankRepository {
    private static final String GET_ALL = "SELECT b FROM Bank b";
    @PersistenceContext
    private EntityManager em;
    public Bank getById(Long id) {
        return em.find(Bank.class, id);
    }

    @Transactional
    public void deleteById(Long id) {
        em.remove(id);
    }

    @Transactional
    public void createById(Bank bank) {
        em.persist(bank);
    }

    @Transactional
    public void updateById(Bank bank) {
        em.merge(bank);

    }

    public List<Bank> getAllBanks() {
        return em.createQuery(GET_ALL, Bank.class).getResultList();
    }
}
