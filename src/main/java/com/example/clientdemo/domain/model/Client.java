package com.example.clientdemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTS", schema = "dbo")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Transient
    private List<Event> eventsPresent = new ArrayList<>();

    public Client addEvent(Event event){
        eventsPresent.add(event);
        return this;
    }

    public Client removeEvent(Event event){
        eventsPresent.remove(event);
        return this;
    }
}
