package com.example.clientdemo.application.controller;

import com.example.clientdemo.application.controller.dto.EventRequest;
import com.example.clientdemo.domain.model.Event;
import com.example.clientdemo.domain.services.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/events", consumes = "application/json")
public class EventController {

    private final EventService eventService;

    private final ModelMapper mapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<Event> get(@PathVariable int id){
        return ResponseEntity.ok(eventService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.ok(eventService.getAll());
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody EventRequest request){

        Event event = mapper.map(request, Event.class);

        return new ResponseEntity<>(eventService.create(event), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Event> update(@PathVariable int id, @RequestBody EventRequest request){

        Event event = mapper.map(request, Event.class);

        return new ResponseEntity<>(eventService.update(id, event), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity delete(@PathVariable int id){

        eventService.remove(id);
        return ResponseEntity.accepted().build();
    }

}
