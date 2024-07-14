package com.kharitonov.bankAppTest.repository.impl;


import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.entity.Credit;
import com.kharitonov.bankAppTest.entity.LoanOffer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LoanOfferRepository {
    private static final String GET_ALL = "SELECT b FROM LoanOffer b";
    private static final String GET_ALL_BY_CLIENT_ID = "SELECT b FROM LoanOffer b WHERE b.client.id = :clientId";
    @PersistenceContext
    private EntityManager em;
    public LoanOffer getById(Long id) {
        return em.find(LoanOffer.class, id);
    }

    @Transactional
    public void deleteById(LoanOffer loanOfferffer) {
        em.remove(loanOfferffer);
    }

    @Transactional
    public void createById(LoanOffer loanOffer) {
        em.persist(loanOffer);
    }

    @Transactional
    public void updateById(LoanOffer loanOffer) {
        em.merge(loanOffer);

    }

    public List<LoanOffer> getAllOffers() {
        return em.createQuery(GET_ALL, LoanOffer.class).getResultList();
    }

    public List<LoanOffer> getAllOffersByClientId(Long clientId) {
        return em.createQuery(GET_ALL_BY_CLIENT_ID, LoanOffer.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

}
