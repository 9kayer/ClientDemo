package com.example.clientdemo.application.services;

import com.example.clientdemo.domain.model.Client;
import com.example.clientdemo.infrastructure.exception.ClientNotFoundException;
import com.example.clientdemo.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client get(String id) {
        return clientRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(String id, Client update) {
        Client client = clientRepository.findById(id)
                .map(
                    item ->
                    item.toBuilder()
                        .name(update.getName())
                        .address(update.getAddress())
                        .email(update.getEmail())
                        .phoneNumber(update.getPhoneNumber())
                        .notes(update.getNotes())
                        .zipcode(update.getZipcode())
                        .country(update.getCountry())
                        .build()
                )
                .orElseThrow(() -> new ClientNotFoundException(update.getId()));

        return clientRepository.save(client);
    }

    public void remove(String id){
        clientRepository.deleteById(id);

        if (clientRepository.existsById(id)){
            throw new RuntimeException();
        }
    }
}
