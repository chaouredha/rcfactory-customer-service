package org.rcfactory.customerservice.controllers.dto;

import lombok.Data;

@Data
public class CustomerRequestDTO {
    private Long id;
    private String name;
    private String email;
}
