package com.kharitonov.bankAppTest.controller;

import com.kharitonov.bankAppTest.entity.Client;
import com.kharitonov.bankAppTest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public void createClient(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
