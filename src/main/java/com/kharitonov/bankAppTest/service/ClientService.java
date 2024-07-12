package com.kharitonov.bankAppTest.service;

import com.kharitonov.bankAppTest.entity.Client;

import com.kharitonov.bankAppTest.repository.impl.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Client getClientById(Long id) {
        return clientRepository.getById(id);
    }

    public void saveClient(Client client) {
        clientRepository.createById(client);
    }

    public void updateClient(Client client){
        clientRepository.updateById(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
