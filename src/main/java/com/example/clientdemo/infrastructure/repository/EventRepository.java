package com.example.clientdemo.infrastructure.repository;

import com.example.clientdemo.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Integer> {
}
