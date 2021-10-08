package org.rcfactory.customerservice.services;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.rcfactory.customerservice.controllers.dto.CustomerRequestDTO;
import org.rcfactory.customerservice.controllers.dto.CustomerResponseDTO;
import org.rcfactory.customerservice.exceptions.CustomerNotFountException;
import org.rcfactory.customerservice.exceptions.CustomerServiceException;
import org.rcfactory.customerservice.mappers.CustomerMapper;
import org.rcfactory.customerservice.repositories.CustomerRepository;
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
                .orElseThrow(() -> {
                    try {
                        return new CustomerNotFountException(
                                format("L'id %s non trouvé sur l'instance %s : et par le thread %s", id, InetAddress.getLocalHost().getHostAddress(), Thread.currentThread()));
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }
}
