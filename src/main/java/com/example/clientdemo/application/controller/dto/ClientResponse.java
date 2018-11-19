package com.example.clientdemo.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {

    private String id;

    private String name;

    private String email;

    private String phoneNumber;

    private String notes;

    private String address;

    private String zipcode;

    private String country;

    private List<ClientEventResponse> events = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ClientEventResponse{

        private int id;

        private String name;

        private LocalDateTime date;
    }
}
