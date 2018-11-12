package com.example.clientdemo.infrastructure.repository;

import com.example.clientdemo.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
