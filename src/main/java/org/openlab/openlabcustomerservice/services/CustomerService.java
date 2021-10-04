package org.openlab.openlabcustomerservice.services;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.openlab.openlabcustomerservice.controllers.dto.CustomerRequestDTO;
import org.openlab.openlabcustomerservice.controllers.dto.CustomerResponseDTO;
import org.openlab.openlabcustomerservice.exceptions.CustomerNotFountException;
import org.openlab.openlabcustomerservice.exceptions.CustomerServiceException;
import org.openlab.openlabcustomerservice.mappers.CustomerMapper;
import org.openlab.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository custumerRepository,
            CustomerMapper customerMapper) {
        this.customerRepository = custumerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerResponseDTO> customers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerResponseDTO)
                .collect(toList());
    }

    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        return Optional.ofNullable(customerRequestDTO)
                .map(customerMapper::customerRequestDTOcustomerTo)
                .map(customerRepository::save)
                .map(customerMapper::customerToCustomerResponseDTO)
                .orElseThrow(() -> new CustomerServiceException("erreur l'or de l'enregistrement."));
    }

    public CustomerResponseDTO customer(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerResponseDTO)
                .orElseThrow(() -> new CustomerNotFountException(format("L'id %s non trouvé", id)));
    }
}
