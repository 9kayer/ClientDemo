package com.example.clientdemo.application.services;

import com.example.clientdemo.domain.model.Client;
import com.example.clientdemo.infrastructure.repository.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    private ClientService service;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        service = new ClientService(repository);
    }

    @Test
    public void get() {
        Client expectedClient = createClient();
        given(repository.findById(anyString())).willReturn(Optional.of(expectedClient));

        Client client = service.get("1");

        then(repository).should().findById(anyString());
        Assertions.assertThat(client).isEqualTo(expectedClient);
    }

    @Test
    public void getAll() {
        List<Client> expectedClientList = createAllClient();
        given(repository.findAll()).willReturn(expectedClientList);

        List<Client> clientList = service.getAll();

        then(repository).should().findAll();
        Assertions.assertThat(clientList).isEqualTo(expectedClientList);
    }

    @Test
    public void create() {

        Client client = createClient();
        given(repository.save(any(Client.class))).willReturn(client);

        Client returnClient = service.create(client);

        then(repository).should().save(any(Client.class));
        Assertions.assertThat(client).isEqualTo(returnClient);
    }

    @Test
    public void update() {

        Client client = createClient();
        given(repository.save(any(Client.class))).willReturn(client);
        given(repository.findById(anyString())).willReturn(Optional.of(client));

        Client returnClient = service.update(client.getId(), client);

        then(repository).should().findById(anyString());
        then(repository).should().save(any(Client.class));
        Assertions.assertThat(client).isEqualTo(returnClient);
    }

    @Test
    public void remove() {

        given(repository.existsById(anyString())).willReturn(false);

        service.remove("1");

        then(repository).should().existsById(anyString());
        then(repository).should().deleteById(anyString());
    }

    private Client createClient() {
        Client client = Client.builder()
                .id("1")
                .name("get client")
                .build();
        return client;
    }

    private List<Client> createAllClient() {
        Client client1 = Client.builder()
                .id("1")
                .name("get All client 1")
                .build();
        Client client2 = Client.builder()
                .id("2")
                .name("get All client 2")
                .build();
        return Arrays.asList(client1, client2);
    }
}