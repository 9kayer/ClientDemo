package com.example.clientdemo.application.services;

import com.example.clientdemo.domain.model.Client;
import com.example.clientdemo.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client get(int id) {
        return clientRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(int id, Client update) {
        Client client = clientRepository.findById(id)
                .map(
                    item -> item.toBuilder()
                                .name(update.getName())
                                .build()
                )
                .orElseThrow(RuntimeException::new);

        return clientRepository.save(client);
    }

    public void remove(int id){
        clientRepository.deleteById(id);

        if (clientRepository.existsById(id)){
            throw new RuntimeException();
        }
    }
}
