package com.example.clientdemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events", schema = "dbo")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(generator = "event_generator")
    @GenericGenerator(name = "event_generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    private OffsetDateTime date;

    private String location;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "events")
    private List<Client> clients = new ArrayList<>();

    public Event addClient(Client client) {
        clients.add(client);
        return this;
    }

    public Event removeClient(Client client){
        clients.remove(client);
        return this;
    }
}
