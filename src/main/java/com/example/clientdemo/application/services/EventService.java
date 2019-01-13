package com.example.clientdemo.application.services;

import com.example.clientdemo.domain.model.Event;
import com.example.clientdemo.infrastructure.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;


    public Event get(String id) {
        return eventRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event update(String id, Event update) {
        Event event = eventRepository.findById(id)
                .map(
                        item -> item.toBuilder()
                                .name(update.getName())
                                .date(update.getDate())
                                .build()
                )
                .orElseThrow(RuntimeException::new);

        return eventRepository.save(event);
    }

    public void remove(String id){
        eventRepository.deleteById(id);

        if (eventRepository.existsById(id)){
            throw new RuntimeException();
        }
    }
}
