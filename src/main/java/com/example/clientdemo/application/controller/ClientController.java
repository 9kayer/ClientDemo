package com.example.clientdemo.application.controller;

import com.example.clientdemo.application.controller.dto.ClientRequest;
import com.example.clientdemo.domain.model.Client;
import com.example.clientdemo.domain.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/clients", consumes = "application/json")
public class ClientController {

    private final ClientService clientService;

    private final ModelMapper mapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<Client> get(@PathVariable int id){
        return ResponseEntity.ok(clientService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        return ResponseEntity.ok(clientService.getAll());
    }

    @PostMapping
    public ResponseEntity<Client> create( @RequestBody ClientRequest request){

        Client client = mapper.map(request, Client.class);

        return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Client> update(@PathVariable int id, @RequestBody ClientRequest request){

        Client client = mapper.map(request, Client.class);

        return new ResponseEntity<>(clientService.update(id, client), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity delete(@PathVariable int id){

        clientService.remove(id);
        return ResponseEntity.accepted().build();
    }

}
