package com.example.clientdemo.application.controller;

import com.example.clientdemo.application.controller.dto.ClientRequest;
import com.example.clientdemo.application.controller.dto.ClientResponse;
import com.example.clientdemo.domain.model.Client;
import com.example.clientdemo.application.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/clients", consumes = "application/json")
public class ClientController {

    private final ClientService clientService;

    private final ModelMapper mapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<ClientResponse> get(@PathVariable int id){

        Client client = clientService.get(id);

        return ResponseEntity.ok(mapper.map(client, ClientResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll(){

        List<ClientResponse> clientResponsesList =
                clientService.getAll()
                    .stream()
                    .map( client -> mapper.map(client, ClientResponse.class) )
                    .collect(Collectors.toList());

        return ResponseEntity.ok(clientResponsesList);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create( @RequestBody ClientRequest request){

        Client client = mapper.map(request, Client.class);

        return new ResponseEntity<>(
                mapper.map(clientService.create(client), ClientResponse.class),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable int id, @RequestBody ClientRequest request){

        Client client = mapper.map(request, Client.class);

        return new ResponseEntity<>(
                mapper.map(clientService.update(id, client), ClientResponse.class),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity delete(@PathVariable int id){

        clientService.remove(id);
        return ResponseEntity.accepted().build();
    }

}
