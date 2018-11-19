package com.example.clientdemo.application.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    private String name;

    private String email;

    private String phoneNumber;

    private String notes;

    private String address;

    private String zipcode;

    private String country;
}
