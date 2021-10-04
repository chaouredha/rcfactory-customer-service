package org.openlab.openlabcustomerservice.controllers.dto;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String email;
}
