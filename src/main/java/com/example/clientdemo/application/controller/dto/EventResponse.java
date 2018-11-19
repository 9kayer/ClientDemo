package com.example.clientdemo.application.controller.dto;

import com.example.clientdemo.domain.model.Client;
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
public class EventResponse {

    private String id;

    private String name;

    private LocalDateTime date;

    private List<EventClientResponse> clients = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EventClientResponse{

        private int id;

        private String name;

        private String email;

        private String phoneNumber;
    }
}
