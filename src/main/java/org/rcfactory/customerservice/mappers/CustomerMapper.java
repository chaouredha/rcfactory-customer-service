package org.rcfactory.customerservice.mappers;

import org.mapstruct.Mapper;
import org.rcfactory.customerservice.controllers.dto.CustomerRequestDTO;
import org.rcfactory.customerservice.controllers.dto.CustomerResponseDTO;
import org.rcfactory.customerservice.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);

    Customer customerRequestDTOcustomerTo(CustomerRequestDTO customerRequestDTO);
}
