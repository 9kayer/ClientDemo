package com.example.clientdemo.application.controller;

import com.example.clientdemo.application.controller.dto.EventRequest;
import com.example.clientdemo.application.controller.dto.EventResponse;
import com.example.clientdemo.domain.model.Event;
import com.example.clientdemo.application.services.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/events", consumes = "application/json")
public class EventController {

    private final EventService eventService;

    private final ModelMapper mapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<EventResponse> get(@PathVariable String id) {
        return ResponseEntity.ok(mapper.map(eventService.get(id), EventResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAll() {

        List<EventResponse> eventResponseList =
                eventService.getAll()
                        .stream()
                        .map(event -> mapper.map(event, EventResponse.class))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(eventResponseList);
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(@RequestBody EventRequest request) {

        Event event = mapper.map(request, Event.class);

        return new ResponseEntity<>(
                mapper.map(eventService.create(event), EventResponse.class),
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EventResponse> update(@PathVariable String id, @RequestBody EventRequest request) {

        Event event = mapper.map(request, Event.class);

        return new ResponseEntity<>(
                mapper.map(eventService.update(id, event), EventResponse.class),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity delete(@PathVariable String id) {

        eventService.remove(id);
        return ResponseEntity.accepted().build();
    }

}
