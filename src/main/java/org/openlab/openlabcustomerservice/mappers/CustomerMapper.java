package org.openlab.openlabcustomerservice.mappers;

import org.mapstruct.Mapper;
import org.openlab.openlabcustomerservice.controllers.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.controllers.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservice.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);

    Customer customerRequestDTOcustomerTo(CustomerRequestDTO customerRequestDTO);
}
