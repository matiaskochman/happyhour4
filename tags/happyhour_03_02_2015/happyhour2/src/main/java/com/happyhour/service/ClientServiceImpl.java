package com.happyhour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Client;
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
    public long countAllClients() {
        return Client.countClients();
    }
    
    public void deleteClient(Client client) {
        client.remove();
    }
    
    public Client findClient(Long id) {
        return Client.findClient(id);
    }
    
    public List<Client> findAllClients() {
        return Client.findAllClients();
    }
    
    public List<Client> findClientEntries(int firstResult, int maxResults) {
        return Client.findClientEntries(firstResult, maxResults);
    }
    
    public void saveClient(Client client) {
        client.persist();
    }
    
    public Client updateClient(Client client) {
        return client.merge();
    }
	
}
