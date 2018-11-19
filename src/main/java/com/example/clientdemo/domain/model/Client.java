package com.example.clientdemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients", schema = "dbo")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(generator = "client_generator")
    @GenericGenerator(name = "client_generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String notes;

    private String address;

    private String zipcode;

    private String country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_events",
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = { @JoinColumn(name = "event_id") }
    )
    private List<Event> events = new ArrayList<>();

    public Client addEvent(Event event) {
        events.add(event);
        return this;
    }

    public Client removeEvent(Event event) {
        events.remove(event);
        return this;
    }
}
