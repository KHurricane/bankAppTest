package com.kharitonov.bankAppTest.repository.impl;

import com.kharitonov.bankAppTest.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    private static final String GET_ALL = "SELECT c FROM Client c";
    @PersistenceContext
    private EntityManager em;
    public Client getById(Long id) {
        return em.find(Client.class, id);
    }

    @Transactional
    public void deleteById(Client client) {
        em.remove(client);
    }

    @Transactional
    public void createById(Client client) {
        em.persist(client);
    }

    @Transactional
    public void updateById(Client client) {
        em.merge(client);

    }

    public List<Client> getAllClients() {
        return em.createQuery(GET_ALL, Client.class).getResultList();
    }

}
