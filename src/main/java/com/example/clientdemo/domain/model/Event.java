package com.example.clientdemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EVENTS", schema = "dbo")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDateTime date;

    @Transient
    private List<Client> clientsPresent = new ArrayList<>();

    public Event addClient(Client client) {
        clientsPresent.add(client);
        return this;
    }

    public Event removeClient(Client client){
        clientsPresent.remove(client);
        return this;
    }
}
